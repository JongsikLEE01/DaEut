package com.daeut.daeut.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daeut.daeut.partner.service.PartnerService;
import com.daeut.daeut.reservation.service.ReservationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/full-calendar")
public class CalendarController {


    @Autowired
    private PartnerService partnerService;

    @PostMapping("/getPartnerSchedule")
    public List<Map<String, String>> getPartnerSchedule(@RequestParam("partnerNo") String partnerNo) {
        List<String> serviceDates = partnerService.getPartnerSchedule(partnerNo);
        List<Map<String, String>> events = new ArrayList<>();

        for (String serviceDate : serviceDates) {
            Map<String, String> event = new HashMap<>();
            event.put("start", serviceDate); // 시작 날짜 및 시간
            events.add(event);
        }

        return events;
    }
}
