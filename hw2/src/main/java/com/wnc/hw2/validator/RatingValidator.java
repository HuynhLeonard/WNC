package com.wnc.hw2.validator;

import com.wnc.hw2.model.Rating;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class RatingValidator implements ConstraintValidator<ValidRating, Rating> {

    @Override
    public boolean isValid(Rating rating, ConstraintValidatorContext context) {
        // Check if the rating is null or if it matches one of the enum values
        if (rating == null) {
            return false; // or true if null is allowed based on your requirements
        }
        // Return true if rating is valid
        return true;
    }
}
