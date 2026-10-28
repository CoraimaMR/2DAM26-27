
/* Actividad: Programa una calculadora básica empleando funciones de orden superior.
Diseña funciones capaces de recibir otras funciones como argumentos y utiliza expresiones
lambda para realizar las diferentes operaciones matemáticas.*/

/* Funciones lambda
En Kotlin, puedes definir funciones anónimas conocidas como funciones lambda.
Las funciones lambda son funciones sin nombre que puedes pasar como
argumentos a otras funciones.
val suma = { a: Int, b: Int -> a + b }
val resta = { a: Int, b: Int -> a - b }
val resultadoSuma = suma(10, 5)
val resultadoResta = resta(10, 5)*/

fun main() {
    val resultadoSuma = operacion(10, 5, ::suma)
    val resultadoResta = operacion(10, 5, ::resta)
    val resultadoDivision = operacion(10, 5, ::division)
    val resultadoMultiplicacion = operacion(10, 5, ::multiplicacion)

    println("Suma: 10 + 5 = $resultadoSuma \n" +
            "Resta: 10 - 5 = $resultadoResta \n" +
            "Division: 10 / 5 = $resultadoDivision \n" +
            "Multiplicación: 10 * 5 = $resultadoMultiplicacion")
}

fun operacion(a: Int, b: Int, funcion: (Int, Int) -> Int): Int {
    return funcion(a, b)
}

fun suma(a: Int, b: Int): Int {
    return a + b
}

fun resta(a: Int, b: Int): Int {
    return a - b
}

fun division(a: Int, b: Int): Int {
    return a / b
}

fun multiplicacion(a: Int, b: Int): Int {
    return a * b
}