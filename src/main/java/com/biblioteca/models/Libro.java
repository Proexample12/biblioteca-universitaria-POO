package com.biblioteca.models;

import com.biblioteca.interfaces.Prestable;
import jakarta.validation.constraints.NotBlank;

/**
 * Clase Libro.
 * Implementa Prestable permitiendo que los libros se presten.
 * Demuestra herencia, polimorfismo y sobrescritura de métodos.
 */
public class Libro extends Material implements Prestable {

    @NotBlank(message = "El autor del libro no puede estar vacío")
    private String autor;

    private boolean prestado;

    /**
     * Constructor completo.
     */
    public Libro(int codigo, String titulo, Integer año, String autor) {
        super(codigo, titulo, año);
        this.autor = autor;
        this.prestado = false;
    }

    /**
     * Constructor por defecto.
     */
    public Libro() {
        this.prestado = false;
    }

    // ==================== GETTERS Y SETTERS ====================

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public boolean estaPrestado() {
        return prestado;
    }

    // ==================== IMPLEMENTACIÓN DE PRESTABLE ====================

    @Override
    public void prestar() {
        if (!prestado) {
            prestado = true;
            System.out.println("✓ Libro '" + getTitulo() + "' ha sido prestado.");
        } else {
            System.out.println("✗ El libro '" + getTitulo() + "' ya está prestado.");
        }
    }

    @Override
    public void devolver() {
        if (prestado) {
            prestado = false;
            System.out.println("✓ Libro '" + getTitulo() + "' ha sido devuelto.");
        } else {
            System.out.println("✗ El libro '" + getTitulo() + "' no estaba prestado.");
        }
    }

    // ==================== SOBRESCRITURA DE MÉTODO ABSTRACTO ====================

    @Override
    public void mostrarInformacion() {
        System.out.println("\n=== LIBRO ===");
        mostrarDatosBasicos();
        System.out.println("Autor: " + autor);
        System.out.println("Estado: " + (prestado ? "Prestado" : "Disponible"));
    }

    @Override
    public String toString() {
        return "Libro{" +
                "codigo=" + getCodigo() +
                ", titulo='" + getTitulo() + '\'' +
                ", año=" + getAño() +
                ", autor='" + autor + '\'' +
                ", prestado=" + prestado +
                '}';
    }
}
