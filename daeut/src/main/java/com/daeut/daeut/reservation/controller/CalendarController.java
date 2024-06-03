package com.daeut.daeut.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daeut.daeut.reservation.service.ReservationService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/full-calendar")
public class CalendarController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservationRead")
    public String showReservationReadPage(Model model) {
        return "reservationRead";
    }
}
