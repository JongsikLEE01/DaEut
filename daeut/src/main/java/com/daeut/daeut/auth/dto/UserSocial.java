package com.daeut.daeut.auth.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserSocial {
    private String id;
    private String username;
    private String provider;
    private String socialId;
    private String name;
    private String email;
    private String picture;
    private Date createdAt;
    private Date updatedAt;
}