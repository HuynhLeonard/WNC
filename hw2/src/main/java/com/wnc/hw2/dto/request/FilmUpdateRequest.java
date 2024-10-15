package com.wnc.hw2.dto.request;

import com.wnc.hw2.model.Rating;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilmUpdateRequest {
    @Size(min = 1, max = 255, message = "FILM_TITLE_INVALID")
    private String title;

    @Size(max = 1000, message = "FILM_DESCRIPTION_TOO_LONG")
    private String description;

    @Min(value = 1900, message = "FILM_RELEASE_YEAR_INVALID")
    @Max(value = 2100, message = "FILM_RELEASE_YEAR_INVALID")
    private Integer releaseYear;

    @Positive(message = "INVALID_LANGUAGE_ID")
    private Long languageId;

    @Positive(message = "INVALID_LANGUAGE_ID")
    private Long originalLanguageId;

    @Min(value = 1, message = "FILM_RENTAL_DURATION_INVALID")
    @Max(value = 30, message = "FILM_RENTAL_DURATION_INVALID")
    private Integer rentalDuration;

    @DecimalMin(value = "0.00", inclusive = false, message = "FILM_RENTAL_RATE_INVALID")
    private BigDecimal rentalRate;

    @Positive(message = "FILM_LENGTH_INVALID")
    private Integer length;

    @DecimalMin(value = "0.00", inclusive = false, message = "FILM_REPLACEMENT_COST_INVALID")
    private BigDecimal replacementCost;
    
    private Rating rating;

    private String specialFeatures;
}
