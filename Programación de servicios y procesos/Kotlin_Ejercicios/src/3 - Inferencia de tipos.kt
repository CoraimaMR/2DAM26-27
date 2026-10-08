
/* Ejercicio 3. Inferencia de tipos
Declara las siguientes variables sin indicar el tipo explícitamente:
val ciudad = "Madrid"
val habitantes = 3200000
val temperatura = 24.5
val llueve = false
Actividad:
Identifica qué tipo de dato ha inferido Kotlin para cada variable.
Muestra los valores por pantalla. */

fun main() {

    val ciudad = "Madrid"       // Infiere String
    val habitantes = 3200000    // Infiere Int
    val temperatura = 24.5      // Infiere Double
    val llueve = false          // Infiere Boolean

    println("\n--- Mostramos los datos ---")
    println("Ciudad: $ciudad")
    println("Habitantes: $habitantes")
    println("Temperatura: $temperatura")
    println("¿Llueve?: $llueve")

}