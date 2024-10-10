package com.wnc.hw2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ActorDTO {
    @NotBlank(message = "This field cannot be blank")
    @Size(min = 1, max = 45, message = "First name must be between 1 and 45 characters")
    private String first_name;

    @NotBlank(message = "Last name is required")
    @Size(min = 1, max = 45, message = "Last name must be between 1 and 45 characters")
    private String last_name;
}
