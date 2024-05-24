package com.daeut.daeut.auth.dto;

import lombok.Data;

@Data
public class UserAuth {
    private int authNo;
    private int userNo;
    private String auth;
}