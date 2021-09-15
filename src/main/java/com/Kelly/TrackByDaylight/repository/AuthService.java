package com.Kelly.TrackByDaylight.repository;

import com.Kelly.TrackByDaylight.model.ERole;
import com.Kelly.TrackByDaylight.model.Role;
import com.Kelly.TrackByDaylight.model.User;
import com.Kelly.TrackByDaylight.model.UserDetailsImpl;
import com.Kelly.TrackByDaylight.model.dto.JwtResponse;
import com.Kelly.TrackByDaylight.model.dto.LoginRequest;
import com.Kelly.TrackByDaylight.model.dto.SignUpRequest;
import com.Kelly.TrackByDaylight.security.jwt.JwtUtils;
import com.Kelly.TrackByDaylight.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private  final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public User registerUser(SignUpRequest signUpRequest){
        if (userRepository.findByUsername(signUpRequest.getUsername()).isPresent()){
            throw new RuntimeException("Username is already taken"); //customer exception to return an error in the controller
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            throw new RuntimeException("Email is already taken");
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword()));

        Role role = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("got em"));

        user.setRole(role);

        return userRepository.save(user);
    }


    public JwtResponse authenticateUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())

        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }
}
