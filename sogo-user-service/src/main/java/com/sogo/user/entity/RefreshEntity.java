package com.sogo.user.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="refresh")
public class RefreshEntity {

    @Id
    @Column(name="refresh_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshNo;

    private String idEmail;
    private String refresh;
    private String expiration;
}
