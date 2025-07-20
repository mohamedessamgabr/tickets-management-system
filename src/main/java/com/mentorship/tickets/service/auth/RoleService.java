package com.mentorship.tickets.service.auth;

import com.mentorship.tickets.entity.AppRole;
import com.mentorship.tickets.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final Logger logger = LogManager.getLogger(RoleService.class);
    private final RoleRepository roleRepository;

    @Cacheable(value = "role", key = "#roleName")
    public AppRole getRoleByRoleName(String roleName) {
        AppRole role = roleRepository.findByRoleName(roleName);
        logger.info("Fetching role with name: {} and ID: {}", roleName, role.getRoleId());
        return role;
    }

    public boolean existsByName(String name) {
        return roleRepository.findByRoleName(name) != null;
    }

    public void saveRole(AppRole role) {
        roleRepository.save(role);
    }
}
