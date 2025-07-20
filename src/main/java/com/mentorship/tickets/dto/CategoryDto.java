package com.mentorship.tickets.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CategoryDto implements Serializable {
    @JsonProperty(value = "category_id")
    private Integer id;
    @JsonProperty(value = "name")
    @NotBlank(message = "Category name must have a value")
    private String name;
}
