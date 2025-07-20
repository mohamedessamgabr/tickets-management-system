package com.mentorship.tickets.dto.auth;

import java.io.Serializable;

public record AuthenticationResponse(String token) implements Serializable {}
