/*Actividad
Implementa un sistema de gestión bibliotecaria que haga uso de diferentes colecciones
(Array, List, Map y Set). Incluye operaciones funcionales para administrar información
sobre libros, autores y préstamos.*/

fun main() {

    val libros = arrayOf(
        LibroBiblioteca("978-01", "Kotlin desde cero", "Elena Ruiz"),
        LibroBiblioteca("978-02", "Colecciones prácticas", "Marcos Gil"),
        LibroBiblioteca("978-03", "Kotlin desde cero", "Elena Ruiz")
    )
    val listaLibros = libros.toList()
    val librosPorIsbn = listaLibros.associateBy { it.isbn }
    val autores = listaLibros.map { it.autor }.toSet()
    val prestamos = mapOf("978-02" to "Lucía")
    val disponibles = listaLibros.filter { it.isbn !in prestamos.keys }

    println("Libros (Array): ${libros.contentToString()}")
    println("Libros (List): $listaLibros")
    println("Autores únicos (Set): $autores")
    println("Índice por ISBN (Map): $librosPorIsbn")
    println("Préstamos activos: $prestamos")
    println("Libros disponibles: ${disponibles.map { it.titulo }}")
}

data class LibroBiblioteca(val isbn: String, val titulo: String, val autor: String)