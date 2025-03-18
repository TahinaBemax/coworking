package itu.evaluation.s6.validation.annotation;

import itu.evaluation.s6.validation.validator.UniqueCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCodeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface UniqueValue {
    String message() default "Ceci doit être unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
