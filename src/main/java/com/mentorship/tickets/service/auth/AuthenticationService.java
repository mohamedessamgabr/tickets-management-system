package com.mentorship.tickets.service.auth;

import com.mentorship.tickets.dto.auth.AuthenticationRequest;
import com.mentorship.tickets.dto.auth.AuthenticationResponse;
import com.mentorship.tickets.dto.auth.RegisterRequest;
import com.mentorship.tickets.entity.AppUser;
import com.mentorship.tickets.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final Logger logger = LogManager.getLogger(AuthenticationService.class);

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RoleService roleService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = AppUser
                .builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .build();
        user.addRole(Optional.ofNullable(roleService.getRoleByRoleName("USER"))
                .orElseThrow(() -> {
                    logger.error("User role not found");
                    return new IllegalArgumentException("User role not found");
                }));
        userService.saveUser(user);
        var jwtToken = jwtService.generateToken(user);
        logger.info("User {} registered successfully", user.getUsername());
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        var authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.username(),
                                authenticationRequest.password()));
        var user = (AppUser) authentication.getPrincipal();
        var jwtToken = jwtService.generateToken(user);
        logger.info("User {} authenticated successfully", user.getUsername());
        return new AuthenticationResponse(jwtToken);
    }
}
