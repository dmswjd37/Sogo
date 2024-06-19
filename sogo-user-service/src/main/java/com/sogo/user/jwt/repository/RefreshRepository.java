package com.sogo.user.jwt.repository;

import com.sogo.user.entity.RefreshEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface RefreshRepository extends JpaRepository<RefreshEntity, Long> {

    Boolean existsByRefresh(String refresh);

    @Transactional
    void deleteByRefresh(String refresh);
}
