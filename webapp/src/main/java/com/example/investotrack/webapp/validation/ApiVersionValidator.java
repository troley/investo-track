package com.example.investotrack.webapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.stream.Stream;

/**
 * Validates the API version. If our system doesn't recognize the API version, then it's considered not a valid API
 * version.
 */
public class ApiVersionValidator implements ConstraintValidator<ValidApiVersion, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            double version = Double.parseDouble(s);
            if (Stream.of(KnownApiVersion.values()).anyMatch(v -> v.getVersion() == version)) {
                return true;
            }
        } catch (NumberFormatException ignore) {
            return false;
        }
        return false;
    }
}
