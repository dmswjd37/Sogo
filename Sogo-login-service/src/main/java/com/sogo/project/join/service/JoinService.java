package com.sogo.project.join.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sogo.project.jwt.model.JoinDTO;
import com.sogo.project.jwt.model.UserEntity;
import com.sogo.project.jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public void joinProcess(JoinDTO joinDTO) {
	
	    String email = joinDTO.getEmail();
	    String name = joinDTO.getName();
	    String password = joinDTO.getPassword();
	
	    Boolean isExist = userRepository.existsByEmail(email);
	
	    if (isExist) {
	
	        return;
	    }
	
	    UserEntity data = new UserEntity();
	
	    data.setEmail(email);
	    data.setName(name);
	    data.setPassword(bCryptPasswordEncoder.encode(password));
	    data.setRole("ROLE_USER");
	
	    userRepository.save(data);
	}
}
