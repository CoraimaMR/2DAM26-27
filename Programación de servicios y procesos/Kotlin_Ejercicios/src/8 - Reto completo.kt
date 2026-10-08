
/* Ejercicio 8. Reto completo
Desarrolla un programa para gestionar un videojuego con las siguientes variables:
Nombre del jugador.
Nivel actual.
Puntuación.
¿Tiene suscripción premium?
El programa debe:
1.Mostrar los datos iniciales.
2.Incrementar el nivel en 1.
3.Aumentar la puntuación en 500 puntos.
4.Convertir la puntuación a String.
5.Mostrar los datos actualizados. */

fun main() {

    val nombre: String = "Coraima"
    var nivelActual: Int = 1
    var puntuacion: Int = 0
    var premium: Boolean = false

    println("\n--- Datos iniciales ---")
    println("Nombre: $nombre")
    println("Nivel actual: $nivelActual")
    println("Puntuacion: $puntuacion")
    println("¿Es premium?: $premium")

    nivelActual = nivelActual + 1
    puntuacion = puntuacion + 500
    puntuacion.toString()

    println("\n--- Datos actualizados ---")
    println("Nombre: $nombre")
    println("Nivel actual: $nivelActual")
    println("Puntuacion: $puntuacion")
    println("¿Es premium?: $premium")

}