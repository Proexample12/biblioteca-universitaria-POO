package com.biblioteca.models;

import com.biblioteca.annotations.AñoPublicacionValido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * Clase abstracta Material.
 * Implementa el concepto de clase abstracta y encapsulamiento.
 * Todos los materiales de la biblioteca heredan de esta clase.
 */
public abstract class Material {

    @Positive(message = "El código debe ser un número positivo")
    private int codigo;

    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;

    @AñoPublicacionValido
    private Integer año;

    /**
     * Constructor completo.
     */
    public Material(int codigo, String titulo, Integer año) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.año = año;
    }

    /**
     * Constructor por defecto.
     */
    public Material() {
    }

    // ==================== GETTERS Y SETTERS ====================

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer año) {
        this.año = año;
    }

    // ==================== MÉTODO ABSTRACTO ====================

    /**
     * Método abstracto que debe ser implementado por las subclases.
     * Demuestra polimorfismo y sobreescritura.
     */
    public abstract void mostrarInformacion();

    /**
     * Método concreto que puede ser usado por todas las subclases.
     */
    public void mostrarDatosBasicos() {
        System.out.println("Código: " + codigo);
        System.out.println("Título: " + titulo);
        System.out.println("Año de Publicación: " + año);
    }
}
