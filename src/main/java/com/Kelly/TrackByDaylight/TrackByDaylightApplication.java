package com.Kelly.TrackByDaylight;

import com.Kelly.TrackByDaylight.model.ERole;
import com.Kelly.TrackByDaylight.model.Role;
import com.Kelly.TrackByDaylight.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrackByDaylightApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackByDaylightApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository){
		return args -> {
			roleRepository.save(new Role(ERole.ROLE_USER));
			roleRepository.save(new Role(ERole.ROLE_MOD));
			roleRepository.save(new Role(ERole.ROLE_ADMIN));

		};
	}

}
