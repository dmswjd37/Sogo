package com.sogo.user.oauth2.service;

import com.sogo.user.entity.UserEntity;
import com.sogo.user.jwt.repository.UserRepository;
import com.sogo.user.oauth2.model.GoogleResponse;
import com.sogo.user.oauth2.model.CustomOAuth2User;
import com.sogo.user.oauth2.model.OAuth2Response;
import com.sogo.user.oauth2.model.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        //응답
        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println("oAuth2User="+oAuth2User);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("google")) {

            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else {

            return null;
        }

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String usersns = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        UserEntity existData = userRepository.findByUsersns(usersns);

        //sns 정보가 없으면 신규 저장
        if (existData == null) {

            UserEntity userEntity = new UserEntity();
            userEntity.setUsersns(usersns);
            userEntity.setIdEmail(oAuth2Response.getEmail());
            userEntity.setUsername(oAuth2Response.getName());
            userEntity.setRole("ROLE_USER");

            userRepository.save(userEntity);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsersns(usersns);
            userDTO.setUsername(oAuth2Response.getName());
            userDTO.setRole("ROLE_USER");

            return new CustomOAuth2User(userDTO);

        //sns 정보가 있으면 업데이트
        } else {

            existData.setIdEmail(oAuth2Response.getEmail());
            existData.setUsername(oAuth2Response.getName());

            userRepository.save(existData);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsersns(existData.getUsersns());
            userDTO.setUsername(oAuth2Response.getName());
            userDTO.setRole(existData.getRole());

            return new CustomOAuth2User(userDTO);
        }
    }
}
