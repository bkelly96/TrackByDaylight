package com.Kelly.TrackByDaylight.security.jwt;

import com.Kelly.TrackByDaylight.model.User;
import com.Kelly.TrackByDaylight.model.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {

    private static final String jwtSecret = Base64.getEncoder().encodeToString("superSecretKey".getBytes(StandardCharsets.UTF_8));

    private final int jwtExpirationMs = 86400000;


    public String generateJwtToken(Authentication authentication){

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret) //taking our secret and creating the webtoken with it's algorithm to create it's key
                .compact();
    }
}
