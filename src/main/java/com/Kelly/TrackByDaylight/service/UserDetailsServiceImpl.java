package com.Kelly.TrackByDaylight.service;

import com.Kelly.TrackByDaylight.model.User;
import com.Kelly.TrackByDaylight.model.UserDetailsImpl;
import com.Kelly.TrackByDaylight.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor //this autowires
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found +" + username));

        return UserDetailsImpl.build(user);
    }
}
