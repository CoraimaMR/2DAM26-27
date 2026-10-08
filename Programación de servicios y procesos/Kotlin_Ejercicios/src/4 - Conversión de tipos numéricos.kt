
/* Ejercicio 4. Conversión de tipos numéricos
Crea una variable entera con el valor 25.
Realiza las siguientes conversiones:
Int → Double
Int → String
Muestra por pantalla:
Valor original.
Valor convertido.
Tipo de cada variable.
Ayuda: Utiliza las funciones toDouble() y toString(). */

fun main() {

    val numeroInt: Int = 25

    println("\n--- Valor original ---")
    println("Valor: $numeroInt")

    val numeroDouble: Double = numeroInt.toDouble()
    val numeroString: String = numeroInt.toString()

    println("\n--- Valores convertidos ---")
    println("Convertido a Double: $numeroDouble")
    println("Convertido a String: $numeroString")

}