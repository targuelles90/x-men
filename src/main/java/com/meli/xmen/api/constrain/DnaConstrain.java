package com.meli.xmen.api.constrain;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = com.meli.xmen.api.constrain.DnaValidator.class)
@Target(value = {ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DnaConstrain {
    String message() default "Dna is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
