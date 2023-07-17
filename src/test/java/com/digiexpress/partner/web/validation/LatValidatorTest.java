package com.digiexpress.partner.web.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.Test;

class LatValidatorTest {
    @Test
    void testIsValid() {
        assertFalse((new LatValidator()).isValid(10.0d, null));
        assertTrue((new LatValidator()).isValid(90.0d, null));
    }
}

