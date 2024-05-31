package com.daeut.daeut.partner.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Parther {
    private int partnerNo;
    private int partnerGrade;
    private int partnerReserve;
    private Date partnerCareer;
    private String introduce;
}