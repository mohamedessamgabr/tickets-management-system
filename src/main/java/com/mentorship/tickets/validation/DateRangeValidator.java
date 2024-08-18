package com.mentorship.tickets.validation;

import com.mentorship.tickets.dto.TaskDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, TaskDto> {

    @Override
    public boolean isValid(TaskDto value, ConstraintValidatorContext context) {
        return value.getDateFrom().isBefore(value.getDateTo()) ||
                value.getDateTo().equals(value.getDateFrom());
    }
}
