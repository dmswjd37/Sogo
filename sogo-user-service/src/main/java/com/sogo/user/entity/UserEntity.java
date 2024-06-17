package com.sogo.user.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Table(name="user")
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name="id_email", length = 255)
    private String idEmail;

    @Column(length = 50)
    private String username;

    @Column(length = 500)
    private String password;

    @Column(length = 50)
    private String role;
}
