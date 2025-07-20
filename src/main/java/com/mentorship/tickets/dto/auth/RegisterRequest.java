package com.mentorship.tickets.dto.auth;

import java.io.Serializable;

public record RegisterRequest(String firstName,
                              String username,
                              String lastName,
                              String password) implements Serializable {}
