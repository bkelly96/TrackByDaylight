package com.Kelly.TrackByDaylight;

import com.Kelly.TrackByDaylight.model.ERole;
import com.Kelly.TrackByDaylight.model.Role;
import com.Kelly.TrackByDaylight.model.User;
import com.Kelly.TrackByDaylight.repository.RoleRepository;
import com.Kelly.TrackByDaylight.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TrackByDaylightApplication {


	public static void main(String[] args) {
		SpringApplication.run(TrackByDaylightApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
		return args -> {
			roleRepository.save(new Role(ERole.ROLE_USER));
			roleRepository.save(new Role(ERole.ROLE_MOD));
			roleRepository.save(new Role(ERole.ROLE_ADMIN));

			User user = new User("laughenstein", "brian@guy.com", passwordEncoder.encode("password"));

			Role role = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("got em"));

			user.setRole(role);
			userRepository.save(user);

		};
	}

}
