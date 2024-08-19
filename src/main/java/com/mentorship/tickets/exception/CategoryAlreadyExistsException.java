package com.mentorship.tickets.exception;

import jakarta.validation.constraints.NotBlank;

public class CategoryAlreadyExistsException extends RuntimeException {
    public CategoryAlreadyExistsException() {
        super();
    }
    public CategoryAlreadyExistsException(String message) {
        super(message);
    }

}
