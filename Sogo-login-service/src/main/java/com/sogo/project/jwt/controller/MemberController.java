package com.sogo.project.jwt.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sogo.project.entity.RefreshEntity;
import com.sogo.project.join.service.JoinService;
import com.sogo.project.jwt.JWTUtil;
import com.sogo.project.jwt.model.JoinDTO;
import com.sogo.project.jwt.repository.RefreshRepository;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Validated
public class MemberController {
	
	private final JoinService joinService;
	private final JWTUtil jwtUtil;
	private final RefreshRepository refreshRepository;

    @PostMapping("/join")
    public ResponseEntity<JoinDTO> joinProcess(@RequestBody @Valid JoinDTO joinDTO) {
    	
        joinService.joinProcess(joinDTO);

        return new ResponseEntity<JoinDTO>(joinDTO, HttpStatus.CREATED);
    }
    
    //aceess토큰 만료시 재생성
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {

        //get refresh token
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("refresh")) {

                refresh = cookie.getValue();
            }
        }

        if (refresh == null) {

            //response status code
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        }

        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {

            //response status code
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
        }

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refresh")) {

            //response status code
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }
        
        //DB에 저장되어 있는지 확인
  		Boolean isExist = refreshRepository.existsByRefresh(refresh);
  		if (!isExist) {
  		
  		    //response body
  		    return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
  		}

        String email = jwtUtil.getEmail(refresh);
        String name = jwtUtil.getName(refresh);
        String role = jwtUtil.getRole(refresh);

        //make new JWT
        String newAccess = jwtUtil.createJwt("access", email, role, name, 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", email, role, name, 86400000L);

        //Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
  		refreshRepository.deleteByRefresh(refresh);
  		addRefreshEntity(email, newRefresh, 86400000L);
        
        //response
        response.setHeader("access", newAccess);
        response.addCookie(createCookie("refresh", newRefresh));

        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    //Refresh 토큰 저장
    private void addRefreshEntity(String email, String refresh, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setEmail(email);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        refreshRepository.save(refreshEntity);
    }
    
    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
