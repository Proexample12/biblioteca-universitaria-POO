package com.biblioteca.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Year;

/**
 * Validador personalizado para la anotación @AñoPublicacionValido.
 * Valida que el año esté entre 1900 y el año actual.
 */
public class AñoPublicacionValidator implements ConstraintValidator<AñoPublicacionValido, Integer> {

    private static final int AÑO_MINIMO = 1900;

    @Override
    public void initialize(AñoPublicacionValido constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // Si el valor es nulo, se considera válido (otra anotación puede validar @NotNull)
        if (value == null) {
            return true;
        }

        int añoActual = Year.now().getValue();
        return value >= AÑO_MINIMO && value <= añoActual;
    }
}
