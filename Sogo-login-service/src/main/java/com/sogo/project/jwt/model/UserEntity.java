package com.sogo.project.jwt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="member")
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;

    @Column(length = 255)
    private String email;
    
    @Column(length = 50)
    private String name;
    
    @Column(length = 500)
    private String password;

    @Column(length = 50)
    private String role;
}
