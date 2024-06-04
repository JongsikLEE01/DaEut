package com.aloha.test.dto;

import lombok.Data;

@Data
public class UserAuth {
    private int authNo;
    private int userNo;
    private String auth;
}