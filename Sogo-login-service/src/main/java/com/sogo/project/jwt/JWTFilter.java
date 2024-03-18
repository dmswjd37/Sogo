package com.sogo.project.jwt;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sogo.project.entity.UserEntity;
import com.sogo.project.jwt.model.CustomUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;

//jwt토큰 검증
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

	private final JWTUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
			
    	// 헤더에서 access키에 담긴 토큰을 꺼냄
    	String accessToken = request.getHeader("access");

    	// 토큰이 없다면 다음 필터로 넘김
    	if (accessToken == null) {

    	    filterChain.doFilter(request, response);

    	    return;
    	}
			
    	// 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
    	try {
    		
    	    jwtUtil.isExpired(accessToken);
    	    
    	} catch (ExpiredJwtException e) {

    	    //response body
    	    PrintWriter writer = response.getWriter();
    	    writer.print("access token expired");

    	    //response status code
    	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    	    return;
    	}

    	// 토큰이 access인지 확인 (발급시 페이로드에 명시)
    	String category = jwtUtil.getCategory(accessToken);

    	if (!category.equals("access")) {

    	    //response body
    	    PrintWriter writer = response.getWriter();
    	    writer.print("invalid access token");

    	    //response status code
    	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    	    return;
    	}
    	
        //토큰에서 username과 role 획득
        String email = jwtUtil.getEmail(accessToken);
        String name = jwtUtil.getName(accessToken);
        String role = jwtUtil.getRole(accessToken);
				
		//userEntity를 생성하여 값 set
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setName(name);
        userEntity.setRole(role);
				
		//UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

		//스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
		//세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

}
