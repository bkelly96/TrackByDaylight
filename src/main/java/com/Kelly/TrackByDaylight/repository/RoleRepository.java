package com.Kelly.TrackByDaylight.repository;

import com.Kelly.TrackByDaylight.model.ERole;
import com.Kelly.TrackByDaylight.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
