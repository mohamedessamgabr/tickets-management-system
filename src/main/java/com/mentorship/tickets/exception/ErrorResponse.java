package com.mentorship.tickets.exception;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String message;
    private Integer statusCode;
    @Builder.Default
    private LocalDateTime dateTime = LocalDateTime.now();
}
