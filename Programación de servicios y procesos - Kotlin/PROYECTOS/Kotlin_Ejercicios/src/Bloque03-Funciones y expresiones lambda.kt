/*Actividad
Programa una calculadora básica empleando funciones de orden superior.
Diseña funciones capaces de recibir otras funciones como argumentos y utiliza
expresiones lambda para realizar las diferentes operaciones matemáticas.*/

fun main() {

    val numero1 = 18.0
    val numero2 = 6.0

    val sumar: (Double, Double) -> Double = { a, b -> a + b }
    val restar: (Double, Double) -> Double = { a, b -> a - b }
    val multiplicar: (Double, Double) -> Double = { a, b -> a * b }
    val dividir: (Double, Double) -> Double = { a, b -> a / b }

    fun calcular(a: Double, b: Double, operacion: (Double, Double) -> Double): Double {
        return operacion(a, b)
    }

    println("$numero1 + $numero2 = ${calcular(numero1, numero2, sumar)}")
    println("$numero1 - $numero2 = ${calcular(numero1, numero2, restar)}")
    println("$numero1 * $numero2 = ${calcular(numero1, numero2, multiplicar)}")
    println("$numero1 / $numero2 = ${calcular(numero1, numero2, dividir)}")
}