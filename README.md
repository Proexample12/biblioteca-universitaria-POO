# Sistema de Gestión de Biblioteca Universitaria POO

Aplicación Java para gestionar materiales de biblioteca, préstamos y validaciones, aplicando correctamente los principios de Programación Orientada a Objetos.

## Características

- ✅ Registro de materiales (Libros, Revistas, Tesis)
- ✅ Gestión de préstamos y devoluciones
- ✅ Búsqueda por código o título
- ✅ Validación de datos con Jakarta Validation y Hibernate Validator
- ✅ Anotaciones personalizadas para validación de años
- ✅ Implementación completa de POO (Herencia, Encapsulamiento, Polimorfismo, etc.)

## Conceptos POO Implementados

1. **Encapsulamiento**: Atributos privados con getters/setters
2. **Herencia**: Clase abstracta `Material` con subclases `Libro`, `Revista`, `Tesis`
3. **Clase Abstracta**: `Material` con método abstracto `mostrarInformacion()`
4. **Sobreescritura (Override)**: Implementación de `mostrarInformacion()` en cada subclase
5. **Interface**: `Prestable` con métodos `prestar()`, `devolver()`, `estaPrestado()`
6. **Sobrecarga (Overload)**: Métodos `buscarMaterial()` con diferentes parámetros
7. **Polimorfismo**: Uso de referencias del tipo padre `Material`
8. **Validaciones con Anotaciones**: Jakarta Validation y Hibernate Validator
9. **Validación Personalizada**: Anotación `@AñoPublicacionValido`

## Estructura del Proyecto

```
src/main/java/com/biblioteca/
├── annotations/           # Anotaciones personalizadas
│   ├── AñoPublicacionValido.java
│   └── AñoPublicacionValidator.java
├── interfaces/            # Interfaces
│   └── Prestable.java
├── models/                # Modelos de datos
│   ├── Material.java      # Clase abstracta base
│   ├── Libro.java
│   ├── Revista.java
│   └── Tesis.java
├── services/              # Lógica de negocio
│   └── BibliotecaService.java
├── exceptions/            # Excepciones personalizadas
│   └── MaterialNoEncontradoException.java
└── app/
    └── Main.java          # Clase principal
```

## Requisitos

- Java 11 o superior
- Maven 3.6 o superior

## Instalación

```bash
git clone https://github.com/Proexample12/biblioteca-universitaria-POO.git
cd biblioteca-universitaria-POO
mvn clean install
```

## Ejecución

```bash
mvn exec:java -Dexec.mainClass="com.biblioteca.app.Main"
```

O compilar y ejecutar directamente:

```bash
mvn clean package
java -jar target/biblioteca-universitaria-poo-1.0.0.jar
```

## Dependencias

- **Jakarta Validation API**: Para validaciones estándar
- **Hibernate Validator**: Implementación de Jakarta Validation
- **JUnit**: Para pruebas unitarias

## Autor

Proyecto desarrollado para aprender POO en Java.
