package com.sogo.user.oauth2.repository;

import com.sogo.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsersns(String usersns);
}
