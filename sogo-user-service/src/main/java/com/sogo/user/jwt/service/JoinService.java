package com.sogo.user.jwt.service;

import com.sogo.user.entity.UserEntity;
import com.sogo.user.jwt.model.JoinDTO;
import com.sogo.user.jwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDTO joinDTO) {

        String idEmail = joinDTO.getIdEmail();
        String username = joinDTO.getUsername();
        String password = joinDTO.getPassword();

        Boolean isExist = userRepository.existsByIdEmail(idEmail);

        if (isExist) {

            return;
        }

        UserEntity data = new UserEntity();

        data.setIdEmail(idEmail);
        data.setUsername(username);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_USER");

        userRepository.save(data);
    }
}
