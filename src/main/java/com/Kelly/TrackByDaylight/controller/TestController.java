package com.Kelly.TrackByDaylight.controller;

import org.springframework.context.annotation.Role;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public String allAccess(){
        return "Public endpoint";
    }

    @GetMapping("/user")
    //@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MOD')")
    public String userTest(){
        return "User endpoint";
    }

    @GetMapping("/mod")
    public String modTest(){
        return "Mod endpoint";
    }

    @GetMapping("/admin")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @RolesAllowed({"ROLE_ADMIN"})
    public String adminTest(){
        return "Admin endpoint";
    }
}
