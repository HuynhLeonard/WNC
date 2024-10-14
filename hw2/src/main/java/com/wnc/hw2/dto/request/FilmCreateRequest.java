package com.wnc.hw2.dto.request;

import com.wnc.hw2.model.Rating;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilmCreateRequest {
    @NotBlank(message = "Film title is required")
    @Size(min = 1, max = 255, message = "FILM_TITLE_INVALID")
    private String title;

    @Size(max = 1000, message = "FILM_DESCRIPTION_TOO_LONG")
    private String description;

    @Min(value = 1900, message = "FILM_RELEASE_YEAR_INVALID")
    @Max(value = 2100, message = "FILM_RELEASE_YEAR_INVALID")
    private Integer releaseYear;

    @NotNull(message = "Language ID is required")
    @Positive(message = "INVALID_LANGUAGE_ID")
    private Long languageId;

    @Positive(message = "INVALID_LANGUAGE_ID")
    private Long originalLanguageId;

    @NotNull(message = "Rental duration is required")
    @Min(value = 1, message = "FILM_RENTAL_DURATION_INVALID")
    private Integer rentalDuration;

    @NotNull(message = "Rental rate is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "FILM_RENTAL_RATE_INVALID")
    private BigDecimal rentalRate;

    @NotNull(message = "Length is required")
    @Positive(message = "FILM_LENGTH_INVALID")
    private Integer length;

    @NotNull(message = "Replacement cost is required")
    @DecimalMin(value = "0.00", inclusive = false, message = "FILM_REPLACEMENT_COST_INVALID")
    private BigDecimal replacementCost;

    @NotNull(message = "Rating is required")
    private Rating rating;

    private Set<String> specialFeatures;
}
