package com.sogo.user.jwt.controller;

import com.sogo.user.jwt.model.JoinDTO;
import com.sogo.user.jwt.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
@Slf4j
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String joinProcess(@RequestBody JoinDTO joinDTO) {

        log.info("IdEmail="+joinDTO.getIdEmail());
        joinService.joinProcess(joinDTO);

        return "ok";
    }
}
