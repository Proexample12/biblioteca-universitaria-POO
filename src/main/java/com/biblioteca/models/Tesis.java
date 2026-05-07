package com.biblioteca.models;

import jakarta.validation.constraints.NotBlank;

/**
 * Clase Tesis.
 * NO implementa Prestable porque las tesis no se pueden prestar.
 * Demuestra herencia y polimorfismo.
 */
public class Tesis extends Material {

    @NotBlank(message = "La universidad no puede estar vacía")
    private String universidad;

    /**
     * Constructor completo.
     */
    public Tesis(int codigo, String titulo, Integer año, String universidad) {
        super(codigo, titulo, año);
        this.universidad = universidad;
    }

    /**
     * Constructor por defecto.
     */
    public Tesis() {
    }

    // ==================== GETTERS Y SETTERS ====================

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    // ==================== SOBRESCRITURA DE MÉTODO ABSTRACTO ====================

    @Override
    public void mostrarInformacion() {
        System.out.println("\n=== TESIS ===");
        mostrarDatosBasicos();
        System.out.println("Universidad: " + universidad);
        System.out.println("Estado: Solo consulta (No disponible para préstamo)");
    }

    @Override
    public String toString() {
        return "Tesis{" +
                "codigo=" + getCodigo() +
                ", titulo='" + getTitulo() + '\'' +
                ", año=" + getAño() +
                ", universidad='" + universidad + '\'' +
                '}';
    }
}
