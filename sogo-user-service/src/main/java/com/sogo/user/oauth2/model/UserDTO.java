package com.sogo.user.oauth2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String role;//권한
    private String username;//이름
    private String usersns;//sns 정보
}
