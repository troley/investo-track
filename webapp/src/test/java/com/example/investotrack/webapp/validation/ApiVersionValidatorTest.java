package com.example.investotrack.webapp.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApiVersionValidatorTest {

    @Mock
    private ConstraintValidatorContext mockConstraintValidatorContext;

    private final ApiVersionValidator apiVersionValidator = new ApiVersionValidator();

    @Test
    void isValid_notANumber_returnsFalse() {
        // Act & Assert
        assertFalse(apiVersionValidator.isValid("nope", mockConstraintValidatorContext));
    }

    @Test
    void isValid_unknownNumber_returnsFalse() {
        // Act & Assert
        assertFalse(apiVersionValidator.isValid("-1", mockConstraintValidatorContext));
    }

    @Test
    void isValid_emptyString_returnsFalse() {
        // Act & Assert
        assertFalse(apiVersionValidator.isValid("", mockConstraintValidatorContext));
    }

    @Test
    void isValid_knownNumber_returnsTrue() {
        // Act & Assert
        assertTrue(apiVersionValidator.isValid("1", mockConstraintValidatorContext));
    }

    @Test
    void isValid_knownNumberAsDouble_returnsTrue() {
        // Act & Assert
        assertTrue(apiVersionValidator.isValid("1.0", mockConstraintValidatorContext));
    }
}
