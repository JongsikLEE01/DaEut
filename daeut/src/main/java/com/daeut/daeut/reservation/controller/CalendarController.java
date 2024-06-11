package com.daeut.daeut.reservation.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daeut.daeut.partner.service.PartnerService;
import com.daeut.daeut.reservation.dto.Event;
import com.daeut.daeut.reservation.dto.Services;
import com.daeut.daeut.reservation.service.ReservationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/full-calendar")
public class CalendarController {


    @Autowired
    private PartnerService partnerService;

    @Autowired
    private ReservationService reservationService;

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


    @ResponseBody
    @GetMapping("/events")
    public List<Event> getEvents(@RequestParam("serviceNo") int serviceNo) throws Exception {
        log.info("serviceNo : " + serviceNo);
        Services service = reservationService.select(serviceNo);
        int partnerNo = service.getPartnerNo();

        List<Event> eventList = reservationService.calendarListByServiceNo(partnerNo);

        // 샘플 데이터
        // Calendar calendar = Calendar.getInstance();
        // calendar.set(2024, Calendar.JUNE, 15);
        // Date date1 = calendar.getTime();
        // calendar.set(2024, Calendar.JUNE, 30);
        // Date date2 = calendar.getTime();
        // eventList.add(new Event("일정1", date1, date1, "일정 설명"));
        // eventList.add(new Event("일정2", date2, date2, "일정 설명"));
        return eventList; // 이벤트 데이터를 가져오는 서비스 호출
    }


}
