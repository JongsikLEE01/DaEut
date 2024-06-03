package com.daeut.daeut.partner.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.daeut.daeut.auth.dto.Users;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
public class CustomPartner implements UserDetails {

    private Users user;
    private Partner partner;

    public CustomPartner(Users user,Partner partner) {
        this.user = user;
        this.partner = partner;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return partner.getAuthList().stream()
                                 .map( (auth) -> new SimpleGrantedAuthority(auth.getAuth()) )
                                 .collect(Collectors.toList());
    }

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     Set<String> combinedAuthorities = new HashSet<>();
        
    //     // 파트너의 권한 정보를 가져와서 combinedAuthorities에 추가합니다.
    //     partner.getAuthList().forEach(auth -> combinedAuthorities.add(auth.getAuth()));

    //     // 사용자의 권한 정보를 가져와서 combinedAuthorities에 추가합니다.
    //     user.getAuthList().forEach(auth -> combinedAuthorities.add(auth.getAuth()));

    //     // combinedAuthorities를 GrantedAuthority 객체로 변환하여 반환합니다.
    //     return combinedAuthorities.stream()
    //             .map(SimpleGrantedAuthority::new)
    //             .collect(Collectors.toList());
    // }


    @Override
    public String getPassword() {
        return user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled() == 0 ? false : true;
    }
    
}
