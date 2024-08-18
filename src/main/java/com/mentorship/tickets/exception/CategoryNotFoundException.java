package com.mentorship.tickets.exception;

public class CategoryNotFoundException extends EntityNotFoundException{
    public CategoryNotFoundException() {
        super();
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
