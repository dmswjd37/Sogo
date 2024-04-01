package com.sogo.project.config;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.sogo.project.jwt.CustomLogoutFilter;
import com.sogo.project.jwt.JWTFilter;
import com.sogo.project.jwt.JWTUtil;
import com.sogo.project.jwt.LoginFilter;
import com.sogo.project.jwt.repository.RefreshRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	//AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
	private final AuthenticationConfiguration authenticationConfiguration;
	//JWTUtil 주입
	private final JWTUtil jwtUtil;
	private final RefreshRepository refreshRepository;
	
	@Bean
    public AuthenticationManager authenticationManager
    	(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
        .cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {

            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                CorsConfiguration configuration = new CorsConfiguration();

                configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                configuration.setAllowedMethods(Collections.singletonList("*"));
                configuration.setAllowCredentials(true);
                configuration.setAllowedHeaders(Collections.singletonList("*"));
                configuration.setMaxAge(3600L);

                configuration.setExposedHeaders(Collections.singletonList("Authorization"));
                configuration.setExposedHeaders(Collections.singletonList("access"));

                return configuration;
            }
        })));
		
				//csrf disable
        http
                .csrf((auth) -> auth.disable());

				//From 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

				//http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

				//경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                .antMatchers("/login", "/", "/join").permitAll()
                .antMatchers("/reissue").hasRole("USER")
				.antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated());

        //JWTFilter 등록
        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
        
        //필터 추가 LoginFilter()는 인자를 받음 (AuthenticationManager() 메소드에 authenticationConfiguration 객체를 넣어야 함) 따라서 등록 필요
        //AuthenticationManager()와 JWTUtil 인수 전달
        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), 
                		jwtUtil, refreshRepository), UsernamePasswordAuthenticationFilter.class);

        //로그아웃 필터
        http
        		.addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshRepository), 
        				LogoutFilter.class);
        
        
				//세션 설정
        http
                .sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
