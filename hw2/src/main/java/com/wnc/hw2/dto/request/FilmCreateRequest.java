package com.wnc.hw2.dto.request;

import com.wnc.hw2.model.Rating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilmCreateRequest {
    @NotBlank(message = "Film title is required")
    @Size(min = 1, max = 255, message = "Film title must be between 1 and 255 characters")
    private String title;

    @Size(max = 1000, message = "Description cannot be longer than 1000 characters")
    private String description;

    @Min(value = 1900, message = "Release year must not be before 1900")
    @Max(value = 2100, message = "Release year must not be after 2100")
    private Integer releaseYear;

    @NotNull(message = "Language ID is required")
    @Positive(message = "Language ID must be a positive number")
    private Long languageId;

    @Positive(message = "Original language ID must be a positive number")
    private Long originalLanguageId;

    @NotNull(message = "Rental duration is required")
    @Min(value = 1, message = "Rental duration must be at least 1 day")
    @Max(value = 30, message = "Rental duration must not exceed 30 days")
    private Integer rentalDuration;

    @NotNull(message = "Rental rate is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Rental rate must be greater than 0")
    @Digits(integer = 4, fraction = 2, message = "Rental rate must have up to 4 digits and 2 decimal places")
    private BigDecimal rentalRate;

    @Positive(message = "Length must be a positive number")
    private Integer length;

    @NotNull(message = "Replacement cost is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "Replacement cost must be greater than 0")
    @Digits(integer = 5, fraction = 2, message = "Replacement cost must have up to 5 digits and 2 decimal places")
    private BigDecimal replacementCost;

    @NotNull(message = "Rating is required")
    private Rating rating;

    private String specialFeatures;
}
