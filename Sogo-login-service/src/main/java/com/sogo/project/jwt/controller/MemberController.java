package com.sogo.project.jwt.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sogo.project.join.service.JoinService;
import com.sogo.project.jwt.model.JoinDTO;

import lombok.RequiredArgsConstructor;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Validated
public class MemberController {
	
	private final JoinService joinService;

    @PostMapping("/join")
    public ResponseEntity<JoinDTO> joinProcess(@RequestBody @Valid JoinDTO joinDTO, 
    		BindingResult bindingResult) {

    	if (bindingResult.hasErrors()) {
            return new ResponseEntity<JoinDTO>(HttpStatus.BAD_REQUEST);
        }
    	
        joinService.joinProcess(joinDTO);

        return new ResponseEntity<JoinDTO>(joinDTO, HttpStatus.CREATED);
    }
    
}
