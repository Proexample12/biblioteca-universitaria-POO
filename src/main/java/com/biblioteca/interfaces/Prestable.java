package com.biblioteca.interfaces;

/**
 * Interfaz Prestable define el comportamiento relacionado con préstamos de materiales.
 * Implementa el concepto de Interface en POO.
 */
public interface Prestable {
    /**
     * Realiza el préstamo del material.
     */
    void prestar();

    /**
     * Realiza la devolución del material.
     */
    void devolver();

    /**
     * Verifica si el material está prestado.
     *
     * @return true si está prestado, false en caso contrario
     */
    boolean estaPrestado();
}
