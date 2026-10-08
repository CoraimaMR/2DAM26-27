
/* Ejercicio 1. Datos personales
Crea un programa que almacene la siguiente información de una persona:
Nombre (String)
Edad (Int)
Altura en metros (Double)
¿Es estudiante? (Boolean)
Muestra todos los datos por pantalla utilizando variables declaradas con val.
Ampliación: Cambia algunas variables a var y modifica sus valores antes de mostrarlos. */

fun main() {

    val nombre: String = "Coraima"
    val edad: Int = 20
    val alturaOriginal: Double = 1.62
    val estudiante: Boolean = true

    println("\n--- Datos iniciales ---")
    println("Nombre: $nombre")
    println("Edad: $edad años")
    println("Altura: $alturaOriginal metros")
    println("¿Es estudiante?: $estudiante")


    var edadModificada = 21
    var alturaModificada = 1.63

    println("\n--- Datos modificados ---")
    println("Nombre: $nombre")
    println("Edad actualizada: $edadModificada años")
    println("Altura actualizada: $alturaModificada metros")
    println("¿Es estudiante?: $estudiante")

}