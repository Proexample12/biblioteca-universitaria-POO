package com.biblioteca.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Anotación personalizada para validar el año de publicación.
 * El año no puede ser menor a 1900 ni mayor al año actual.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AñoPublicacionValidator.class)
@Documented
public @interface AñoPublicacionValido {
    String message() default "El año de publicación debe estar entre 1900 y el año actual";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
