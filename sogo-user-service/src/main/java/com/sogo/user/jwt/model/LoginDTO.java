package com.sogo.user.jwt.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDTO {

    private String idEmail;
    private String password;
}
