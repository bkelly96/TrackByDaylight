package com.Kelly.TrackByDaylight.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/user")
    public String userTest(){
        return "User endpoint";
    }

    @GetMapping("/mod")
    public String modTest(){
        return "Mod endpoint";
    }

    @GetMapping("/admin")
    public String adminTest(){
        return "Admin endpoint";
    }
}
