package com.mentorship.tickets.controller.auth;

import com.mentorship.tickets.dto.auth.AuthenticationRequest;
import com.mentorship.tickets.dto.auth.AuthenticationResponse;
import com.mentorship.tickets.dto.auth.RegisterRequest;
import com.mentorship.tickets.service.auth.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        AuthenticationResponse response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<AuthenticationResponse> authenticate(@RequestBody
                                                                                 AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
