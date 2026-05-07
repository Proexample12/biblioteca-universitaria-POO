package com.biblioteca.services;

import com.biblioteca.models.*;
import com.biblioteca.exceptions.MaterialNoEncontradoException;
import com.biblioteca.interfaces.Prestable;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Servicio BibliotecaService.
 * Gestiona la lógica de negocio de la biblioteca.
 * Implementa búsqueda con sobrecarga de métodos (Overload).
 */
public class BibliotecaService {

    private List<Material> materiales;
    private Validator validator;

    public BibliotecaService(Validator validator) {
        this.materiales = new ArrayList<>();
        this.validator = validator;
    }

    /**
     * Agrega un material a la biblioteca después de validarlo.
     *
     * @param material Material a agregar
     * @return true si se agregó correctamente, false si hay errores de validación
     */
    public boolean agregarMaterial(Material material) {
        Set<ConstraintViolation<Material>> violations = validator.validate(material);
        if (!violations.isEmpty()) {
            System.out.println("❌ Errores de validación:");
            violations.forEach(v -> System.out.println("  - " + v.getMessage()));
            return false;
        }
        materiales.add(material);
        System.out.println("✓ Material agregado exitosamente.");
        return true;
    }

    /**
     * Búsqueda SOBRECARGADA: Por código (Overload).
     *
     * @param codigo Código del material
     * @return Material encontrado
     * @throws MaterialNoEncontradoException Si no existe
     */
    public Material buscarMaterial(int codigo) throws MaterialNoEncontradoException {
        return materiales.stream()
                .filter(m -> m.getCodigo() == codigo)
                .findFirst()
                .orElseThrow(() -> new MaterialNoEncontradoException(
                        "Material con código " + codigo + " no encontrado."));
    }

    /**
     * Búsqueda SOBRECARGADA: Por título (Overload).
     *
     * @param titulo Título del material
     * @return Lista de materiales con ese título
     */
    public List<Material> buscarMaterial(String titulo) {
        return materiales.stream()
                .filter(m -> m.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Elimina un material de la biblioteca.
     *
     * @param codigo Código del material a eliminar
     * @return true si se eliminó, false si no existe
     */
    public boolean eliminarMaterial(int codigo) {
        return materiales.removeIf(m -> m.getCodigo() == codigo);
    }

    /**
     * Obtiene todos los materiales.
     *
     * @return Lista de todos los materiales
     */
    public List<Material> obtenerTodosMateriales() {
        return new ArrayList<>(materiales);
    }

    /**
     * Obtiene solo los libros disponibles para préstamo.
     * Demuestra POLIMORFISMO usando Material para acceder a Libro.
     *
     * @return Lista de libros disponibles
     */
    public List<Libro> obtenerLibrosDisponibles() {
        return materiales.stream()
                .filter(m -> m instanceof Libro)
                .map(m -> (Libro) m)
                .filter(l -> !l.estaPrestado())
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las revistas.
     *
     * @return Lista de revistas
     */
    public List<Revista> obtenerRevistas() {
        return materiales.stream()
                .filter(m -> m instanceof Revista)
                .map(m -> (Revista) m)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las tesis.
     *
     * @return Lista de tesis
     */
    public List<Tesis> obtenerTesis() {
        return materiales.stream()
                .filter(m -> m instanceof Tesis)
                .map(m -> (Tesis) m)
                .collect(Collectors.toList());
    }

    /**
     * Muestra la información de todos los materiales.
     * Demuestra POLIMORFISMO: Llamadas a mostrarInformacion() de diferentes tipos.
     */
    public void mostrarTodosMateriales() {
        if (materiales.isEmpty()) {
            System.out.println("\n📚 No hay materiales registrados.");
            return;
        }
        System.out.println("\n📚 MATERIALES DE LA BIBLIOTECA:");
        System.out.println("═".repeat(50));
        materiales.forEach(Material::mostrarInformacion);
        System.out.println("═".repeat(50));
    }

    /**
     * Obtiene estadísticas de la biblioteca.
     */
    public void mostrarEstadisticas() {
        System.out.println("\n📊 ESTADÍSTICAS DE LA BIBLIOTECA:");
        System.out.println("═".repeat(50));
        System.out.println("Total de materiales: " + materiales.size());
        System.out.println("Libros: " + (int) materiales.stream().filter(m -> m instanceof Libro).count());
        System.out.println("Revistas: " + (int) materiales.stream().filter(m -> m instanceof Revista).count());
        System.out.println("Tesis: " + (int) materiales.stream().filter(m -> m instanceof Tesis).count());

        long prestados = materiales.stream()
                .filter(m -> m instanceof Prestable)
                .map(m -> (Prestable) m)
                .filter(Prestable::estaPrestado)
                .count();
        System.out.println("Materiales prestados: " + prestados);
        System.out.println("═".repeat(50));
    }
}
