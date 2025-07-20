package com.mentorship.tickets.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mentorship.tickets.validation.ValidDateRange;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ValidDateRange
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class TaskDto implements Serializable {
    @JsonProperty(value = "task_id")
    private Integer id;
    @JsonProperty(value = "name")
    @NotBlank(message = "Task name must have a value")
    private String name;
    @JsonProperty(value = "description")
    @NotBlank(message = "Task description must have a value")
    private String description;
    @JsonProperty(value = "date_from")
    @NotNull(message = "Task must have a date from")
    private LocalDate dateFrom;
    @JsonProperty(value = "date_to")
    @NotNull(message = "Task must have a date to")
    private LocalDate dateTo;
    @JsonProperty(value = "category")
    @NotBlank(message = "Task must have a category")
    private String category;
}
