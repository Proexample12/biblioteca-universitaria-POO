package com.biblioteca.app;

import com.biblioteca.models.*;
import com.biblioteca.services.BibliotecaService;
import com.biblioteca.exceptions.MaterialNoEncontradoException;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Scanner;

/**
 * Clase Main - Aplicación interactiva de gestión de biblioteca.
 * Demuestra el uso de todos los conceptos POO implementados.
 */
public class Main {

    private static BibliotecaService biblioteca;
    private static Scanner scanner;
    private static Validator validator;

    public static void main(String[] args) {
        inicializarAplicacion();
        mostrarMenuPrincipal();
    }

    private static void inicializarAplicacion() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        biblioteca = new BibliotecaService(validator);
        scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        System.out.println("\n🎓 BIENVENIDO AL SISTEMA DE GESTIÓN DE BIBLIOTECA UNIVERSITARIA");
        System.out.println("═".repeat(60));
    }

    private static void mostrarMenuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n📋 MENÚ PRINCIPAL");
            System.out.println("1. Agregar Material");
            System.out.println("2. Buscar Material");
            System.out.println("3. Préstamo/Devolución");
            System.out.println("4. Ver Todos los Materiales");
            System.out.println("5. Estadísticas");
            System.out.println("6. Eliminar Material");
            System.out.println("7. Salir");
            System.out.print("\nSeleccione una opción: ");

            int opcion = obtenerEntero();
            switch (opcion) {
                case 1:
                    agregarMaterial();
                    break;
                case 2:
                    buscarMaterial();
                    break;
                case 3:
                    gestionarPrestamo();
                    break;
                case 4:
                    biblioteca.mostrarTodosMateriales();
                    break;
                case 5:
                    biblioteca.mostrarEstadisticas();
                    break;
                case 6:
                    eliminarMaterial();
                    break;
                case 7:
                    salir = true;
                    System.out.println("\n¡Hasta luego! 👋");
                    break;
                default:
                    System.out.println("❌ Opción no válida.");
            }
        }
        scanner.close();
    }

    private static void agregarMaterial() {
        System.out.println("\n➕ AGREGAR MATERIAL");
        System.out.println("Seleccione el tipo de material:");
        System.out.println("1. Libro");
        System.out.println("2. Revista");
        System.out.println("3. Tesis");
        System.out.print("Opción: ");

        int tipo = obtenerEntero();
        switch (tipo) {
            case 1:
                agregarLibro();
                break;
            case 2:
                agregarRevista();
                break;
            case 3:
                agregarTesis();
                break;
            default:
                System.out.println("❌ Tipo de material no válido.");
        }
    }

    private static void agregarLibro() {
        System.out.print("Ingrese el código: ");
        int codigo = obtenerEntero();
        System.out.print("Ingrese el título: ");
        String titulo = scanner.next();
        System.out.print("Ingrese el año de publicación: ");
        int año = obtenerEntero();
        System.out.print("Ingrese el autor: ");
        String autor = scanner.next();

        Libro libro = new Libro(codigo, titulo, año, autor);
        biblioteca.agregarMaterial(libro);
    }

    private static void agregarRevista() {
        System.out.print("Ingrese el código: ");
        int codigo = obtenerEntero();
        System.out.print("Ingrese el título: ");
        String titulo = scanner.next();
        System.out.print("Ingrese el año de publicación: ");
        int año = obtenerEntero();
        System.out.print("Ingrese el número de edición: ");
        int numeroEdicion = obtenerEntero();

        Revista revista = new Revista(codigo, titulo, año, numeroEdicion);
        biblioteca.agregarMaterial(revista);
    }

    private static void agregarTesis() {
        System.out.print("Ingrese el código: ");
        int codigo = obtenerEntero();
        System.out.print("Ingrese el título: ");
        String titulo = scanner.next();
        System.out.print("Ingrese el año de publicación: ");
        int año = obtenerEntero();
        System.out.print("Ingrese la universidad: ");
        String universidad = scanner.next();

        Tesis tesis = new Tesis(codigo, titulo, año, universidad);
        biblioteca.agregarMaterial(tesis);
    }

    private static void buscarMaterial() {
        System.out.println("\n🔍 BUSCAR MATERIAL");
        System.out.println("1. Buscar por código");
        System.out.println("2. Buscar por título");
        System.out.print("Opción: ");

        int opcion = obtenerEntero();
        if (opcion == 1) {
            System.out.print("Ingrese el código: ");
            int codigo = obtenerEntero();
            try {
                Material material = biblioteca.buscarMaterial(codigo);
                material.mostrarInformacion();
            } catch (MaterialNoEncontradoException e) {
                System.out.println("❌ " + e.getMessage());
            }
        } else if (opcion == 2) {
            System.out.print("Ingrese el título: ");
            String titulo = scanner.next();
            List<Material> resultados = biblioteca.buscarMaterial(titulo);
            if (resultados.isEmpty()) {
                System.out.println("❌ No se encontraron materiales.");
            } else {
                System.out.println("✓ Se encontraron " + resultados.size() + " resultado(s):");
                resultados.forEach(Material::mostrarInformacion);
            }
        } else {
            System.out.println("❌ Opción no válida.");
        }
    }

    private static void gestionarPrestamo() {
        System.out.println("\n📤 GESTIONAR PRÉSTAMO/DEVOLUCIÓN");
        System.out.println("1. Prestar material");
        System.out.println("2. Devolver material");
        System.out.print("Opción: ");

        int opcion = obtenerEntero();
        System.out.print("Ingrese el código del material: ");
        int codigo = obtenerEntero();

        try {
            Material material = biblioteca.buscarMaterial(codigo);
            if (material instanceof com.biblioteca.interfaces.Prestable) {
                com.biblioteca.interfaces.Prestable prestable = (com.biblioteca.interfaces.Prestable) material;
                if (opcion == 1) {
                    prestable.prestar();
                } else if (opcion == 2) {
                    prestable.devolver();
                } else {
                    System.out.println("❌ Opción no válida.");
                }
            } else {
                System.out.println("❌ Este material no se puede prestar.");
            }
        } catch (MaterialNoEncontradoException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void eliminarMaterial() {
        System.out.print("\nIngrese el código del material a eliminar: ");
        int codigo = obtenerEntero();
        if (biblioteca.eliminarMaterial(codigo)) {
            System.out.println("✓ Material eliminado exitosamente.");
        } else {
            System.out.println("❌ Material no encontrado.");
        }
    }

    private static int obtenerEntero() {
        try {
            return Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.print("❌ Ingrese un número válido: ");
            return obtenerEntero();
        }
    }
}
