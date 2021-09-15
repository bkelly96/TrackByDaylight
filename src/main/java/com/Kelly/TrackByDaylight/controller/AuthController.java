package com.Kelly.TrackByDaylight.controller;


import com.Kelly.TrackByDaylight.model.User;
import com.Kelly.TrackByDaylight.model.dto.LoginRequest;
import com.Kelly.TrackByDaylight.model.dto.SignUpRequest;
import com.Kelly.TrackByDaylight.repository.AuthService;
import com.Kelly.TrackByDaylight.repository.RoleRepository;
import com.Kelly.TrackByDaylight.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> signUp(@Valid @RequestBody SignUpRequest signUpRequest){
        return new ResponseEntity<>(authService.registerUser(signUpRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        return new ResponseEntity<>(authService.authenticateUser(loginRequest), HttpStatus.OK);
    }

}
