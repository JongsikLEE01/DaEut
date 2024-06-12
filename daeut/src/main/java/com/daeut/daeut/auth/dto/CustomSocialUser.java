package com.daeut.daeut.auth.dto;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import lombok.Getter;

@Getter
public class CustomSocialUser extends DefaultOAuth2User {

    private Users user;

    public CustomSocialUser(Users user, OAuthAttributes oAuthAttributes) {
        super(user.getAuthList().stream().map(auth -> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList())
            ,oAuthAttributes.getAttribute()
            ,oAuthAttributes.getNameAttributeKey() )
            ;
        this.user = user;
    }

    public String getName() {
        return user.getUserId();
    }

    public String getEmail() {
        return user.getUserEmail();
    }

    // public String profile() {
    //     return user.getUserProfile();
    // }

}