package com.mentorship.tickets.exception;

public class TaskNotFoundException extends EntityNotFoundException{
    public TaskNotFoundException() {
    }

    public TaskNotFoundException(String message) {
        super(message);
    }
}
