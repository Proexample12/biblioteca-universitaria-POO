package com.biblioteca.models;

import com.biblioteca.interfaces.Prestable;
import jakarta.validation.constraints.Positive;

/**
 * Clase Revista.
 * Implementa Prestable permitiendo que las revistas se presten.
 * Demuestra herencia y polimorfismo.
 */
public class Revista extends Material implements Prestable {

    @Positive(message = "El número de edición debe ser positivo")
    private int numeroEdicion;

    private boolean prestada;

    /**
     * Constructor completo.
     */
    public Revista(int codigo, String titulo, Integer año, int numeroEdicion) {
        super(codigo, titulo, año);
        this.numeroEdicion = numeroEdicion;
        this.prestada = false;
    }

    /**
     * Constructor por defecto.
     */
    public Revista() {
        this.prestada = false;
    }

    // ==================== GETTERS Y SETTERS ====================

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }

    @Override
    public boolean estaPrestado() {
        return prestada;
    }

    // ==================== IMPLEMENTACIÓN DE PRESTABLE ====================

    @Override
    public void prestar() {
        if (!prestada) {
            prestada = true;
            System.out.println("✓ Revista '" + getTitulo() + "' (Edición " + numeroEdicion + ") ha sido prestada.");
        } else {
            System.out.println("✗ La revista '" + getTitulo() + "' ya está prestada.");
        }
    }

    @Override
    public void devolver() {
        if (prestada) {
            prestada = false;
            System.out.println("✓ Revista '" + getTitulo() + "' (Edición " + numeroEdicion + ") ha sido devuelta.");
        } else {
            System.out.println("✗ La revista '" + getTitulo() + "' no estaba prestada.");
        }
    }

    // ==================== SOBRESCRITURA DE MÉTODO ABSTRACTO ====================

    @Override
    public void mostrarInformacion() {
        System.out.println("\n=== REVISTA ===");
        mostrarDatosBasicos();
        System.out.println("Número de Edición: " + numeroEdicion);
        System.out.println("Estado: " + (prestada ? "Prestada" : "Disponible"));
    }

    @Override
    public String toString() {
        return "Revista{" +
                "codigo=" + getCodigo() +
                ", titulo='" + getTitulo() + '\'' +
                ", año=" + getAño() +
                ", numeroEdicion=" + numeroEdicion +
                ", prestada=" + prestada +
                '}';
    }
}
