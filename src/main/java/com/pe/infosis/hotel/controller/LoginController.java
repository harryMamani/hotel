package com.pe.infosis.hotel.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pe.infosis.hotel.jwt.JwtService;

@RestController
@RequestMapping("/seguridad")
public class LoginController {


    private JwtService jwtService;

    @Autowired
    public LoginController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PreAuthorize("authenticated")
    @PostMapping("/login")
    public String login(@AuthenticationPrincipal User activeUser) {
        List<String> roleList = activeUser.getAuthorities().stream().map
                (GrantedAuthority::getAuthority).collect(Collectors.toList());
        return jwtService.createToken(activeUser.getUsername(), roleList);
    }

}
