package com.sogo.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="refreshtoken")
public class RefreshEntity {

	@Id
	@Column(name="refresh_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(length = 255)
    private String email;
	
	@Column(name="refresh_token")
    private String refresh;
    private String expiration;
}
