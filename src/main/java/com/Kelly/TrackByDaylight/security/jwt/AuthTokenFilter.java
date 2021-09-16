package com.Kelly.TrackByDaylight.security.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!StringUtils.hasText(header) || !header.startsWith("Bearer ")) {
           filterChain.doFilter(request,response);
           return;
        }

        final String token = header.split(" ")[1].trim();
        // Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJicmFpbiIsImlhdCI6MTYzMTczNzkyNSwiZXhwIjoxNjMxODI0MzI1fQ.WKab32u4LRgaN1rytC9v43W_UA7NiDi7mx7Z7ld2cIqu1hW580qPhWZq5UqqA_ZS62TBPFsnb2g7YCfNj6DI4Q
        //splits based on the space adn puts it into an array with 2 indexes.
        if (!jwtUtils.validate(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        String username = jwtUtils.getUsername(token);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
