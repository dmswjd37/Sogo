package com.sogo.project.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sogo.project.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	Boolean existsByEmail(String email);
	
	//username을 받아 DB 테이블에서 회원을 조회하는 메소드 작성
    UserEntity findByEmail(String email);
}
