package com.mentorship.tickets.repository;

import com.mentorship.tickets.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByUsername(String username);

    boolean existsByUsername(String username);
}
