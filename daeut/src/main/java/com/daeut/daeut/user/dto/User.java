package com.daeut.daeut.user.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class User {
    private int userNo;
    private String userName;
    private String userPhone;
    private String userBirth;
    private String userAddress;
    private String userEmail;
    private String userGender;
    private String userId;
    private String userPassword;
    private String userPasswordCheck;
    private String userCoupon;
    private Date regDate;
    private Date updDate;
    private int enabled;

    List<UserAuth> authList;
}
