
/*Actividad: Implementa un sistema de gestión bibliotecaria que
haga uso de diferentes colecciones (Array, List, Map y Set). Incluye
operaciones funcionales para administrar información sobre libros,
autores y préstamos.*/

// Data class para representar la información de los libros
data class Libro(val titulo: String, val autor: String, val anio: Int)

fun main() {
    // 1. Array: Colección de tamaño fijo para almacenar categorías estáticas
    val categorias = arrayOf("Ficción", "Ciencia", "Historia", "Biografía")

    // 2. Set: Conjunto de elementos únicos; descarta duplicados automáticamente (como el segundo "Isaac Asimov")
    val autoresUnicos = setOf("Gabriel García Márquez", "Isaac Asimov", "Isabel Allende", "Isaac Asimov")

    // 3. List: Colección ordenada de elementos de tipo Libro (se puede añadir después más elementos)
    val libros = listOf(
        Libro("Cien años de soledad", "Gabriel García Márquez", 1967),
        Libro("Fundación", "Isaac Asimov", 1951),
        Libro("La casa de los espíritus", "Isabel Allende", 1982)
    )

    // 4. Map: Estructura de pares clave-valor para asociar cada libro prestado con su usuario
    val prestamos = mapOf(
        "Cien años de soledad" to "Ana Gómez",
        "Fundación" to "Carlos Pérez"
    )

    // Operación funcional: filter para obtener solo los libros publicados después de 1960
    val librosRecientes = libros.filter { it.anio > 1960 }

    // Operación funcional: map para extraer únicamente los títulos de los libros
    val titulos = libros.map { it.titulo }

    // Operación funcional: map para transformar los nombres de los autores a mayúsculas
    val autoresMayusculas = autoresUnicos.map { it.uppercase() }

    // Resultados por consola
    println("Categorías: ${categorias.joinToString(", ")}")
    println("Autores únicos: $autoresUnicos")
    println("Libros posteriores a 1960: $librosRecientes")
    println("Títulos: $titulos")
    println("Autores en mayúsculas: $autoresMayusculas")
    println("Préstamos activos: $prestamos")
}