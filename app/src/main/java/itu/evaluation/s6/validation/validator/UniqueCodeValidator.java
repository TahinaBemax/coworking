package itu.evaluation.s6.validation.validator;

import itu.evaluation.s6.validation.annotation.UniqueValue;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashSet;
import java.util.Set;

public class UniqueCodeValidator implements ConstraintValidator<UniqueValue, String> {
    private static final Set<String> existingValues = new HashSet<>();

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {
        if (code == null || existingValues.contains(code)) {
            return false;
        }
        existingValues.add(code);
        return true;
    }
}
