package com.biblioteca.exceptions;

/**
 * Excepción personalizada para cuando no se encuentra un material.
 */
public class MaterialNoEncontradoException extends Exception {
    public MaterialNoEncontradoException(String mensaje) {
        super(mensaje);
    }

    public MaterialNoEncontradoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
