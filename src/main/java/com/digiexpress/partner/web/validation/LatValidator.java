package com.digiexpress.partner.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class LatValidator implements ConstraintValidator<LatValidation, Double> {
    public boolean isValid(Double value, ConstraintValidatorContext cvc) {
        return Objects.nonNull(value) && value >= 90 && value <= 90;
    }
}