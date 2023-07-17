package com.digiexpress.partner.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class LngValidator implements ConstraintValidator<LatValidation, Double> {
    public boolean isValid(Double value, ConstraintValidatorContext cvc) {
        return Objects.nonNull(value) && value >= -180 && value <= 180;
    }
}