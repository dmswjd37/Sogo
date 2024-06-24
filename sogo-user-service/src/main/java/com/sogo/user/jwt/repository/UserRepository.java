package com.sogo.user.jwt.repository;

import com.sogo.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Boolean existsByIdEmail(String idEmail);

    //idEmail을 받아 DB 테이블에서 회원을 조회하는 메소드 작성
    UserEntity findByIdEmail(String idEmail);

    UserEntity findByUsersns(String usersns);
}
