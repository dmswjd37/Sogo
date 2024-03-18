package com.sogo.project.join.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sogo.project.entity.UserEntity;
import com.sogo.project.jwt.model.CustomUserDetails;
import com.sogo.project.jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				
				//DB에서 조회
	    UserEntity userData = userRepository.findByEmail(email);
	    
	    if (userData != null) {
			
			//UserDetails에 담아서 return하면 AutneticationManager가 검증 함
	        return new CustomUserDetails(userData);
	    }
	
	    return null;
	}
}
