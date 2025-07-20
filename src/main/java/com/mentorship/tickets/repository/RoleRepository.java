package com.mentorship.tickets.repository;

import com.mentorship.tickets.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole, Integer> {
    AppRole findByRoleName(String roleName);
}
