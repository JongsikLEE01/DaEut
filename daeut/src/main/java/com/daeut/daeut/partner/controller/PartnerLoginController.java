package com.daeut.daeut.partner.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;

import com.daeut.daeut.auth.dto.Users;
import com.daeut.daeut.auth.service.UserService;
import com.daeut.daeut.partner.dto.Partner;
import com.daeut.daeut.partner.service.PartnerService;

public class PartnerLoginController {
    
    private final UserService userService;
    private final PartnerService partnerService;

    @Autowired
    public PartnerLoginController(UserService userService, PartnerService partnerService) {
        this.userService = userService;
        this.partnerService = partnerService;
    }

    @PostMapping("/login")
    public String login(HttpSession session) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users user = userService.findByUsername(username);
        Partner partner = partnerService.findByUserNo(user.getUserNo());

        if (partner != null) {
            session.setAttribute("partnerNo", partner.getPartnerNo());
        }

        return "redirect:/home";
    }

}
