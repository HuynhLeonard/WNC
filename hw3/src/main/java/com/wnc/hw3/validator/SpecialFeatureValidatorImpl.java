package com.wnc.hw3.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SpecialFeatureValidatorImpl implements ConstraintValidator<ValidSpecialFeatures, Set<String>> {

    // Define allowed special features
    private static final Set<String> ALLOWED_SPECIAL_FEATURES = new HashSet<>(Arrays.asList(
            "Trailers",
            "Commentaries",
            "Deleted Scenes",
            "Behind the Scenes"
    ));

    @Override
    public boolean isValid(Set<String> specialFeatures, ConstraintValidatorContext context) {
        // Allow null or empty sets (if needed)
        if (specialFeatures == null || specialFeatures.isEmpty()) {
            return true; // If you want to allow empty sets
        }

        // Check if all features are in the allowed set
        for (String feature : specialFeatures) {
            if (!ALLOWED_SPECIAL_FEATURES.contains(feature)) {
                // Customize the error message
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("SPECIAL_FEATURE_INVALID")
                        .addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
