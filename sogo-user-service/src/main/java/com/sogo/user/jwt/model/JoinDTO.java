package com.sogo.user.jwt.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDTO {

    private String idEmail;
    private String username;
    private String password;
}
