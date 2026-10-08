
/* Ejercicio 5. Conversión de String a número
Declara una variable:
val edadTexto = "18"
Convierte el contenido a tipo Int y calcula la edad dentro de 5 años.
Muestra todos los resultados. */

fun main() {

    val edadTexto = "18"
    println("Texto: $edadTexto")

    var edadInt: Int = edadTexto.toInt()
    println("Convertido a Int: $edadInt")

    edadInt = edadInt + 5
    println("Despues de 5 años: $edadInt")
}