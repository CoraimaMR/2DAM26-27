
/* Ejercicio 2. Uso de var y val
Declara las siguientes variables:
Nombre de una asignatura.
Número de alumnos matriculados.
Nota media de la clase.
Utiliza val para los datos que no cambian y var para los que sí pueden cambiar.
Después:
1.Incrementa en 3 el número de alumnos.
2.Actualiza la nota media.
3.Muestra los resultados. */

fun main() {

    val nombreAsignatura: String = "Programama con Kotlin"
    var numAlumnos: Int = 21
    var notaMedia: Double = 8.5

    println("\n--- Datos iniciales ---")
    println("Nombre: $nombreAsignatura")
    println("Número de alumnos: $numAlumnos")
    println("Nota media: $notaMedia")

    numAlumnos = numAlumnos + 3
    notaMedia = 9.2

    println("\n--- Datos modificados ---")
    println("Nombre: $nombreAsignatura")
    println("Número de alumnos: $numAlumnos")
    println("Nota media: $notaMedia")

}