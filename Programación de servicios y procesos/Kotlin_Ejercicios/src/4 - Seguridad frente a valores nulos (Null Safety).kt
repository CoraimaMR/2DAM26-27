
/* Actividad: Desarrolla una aplicación de consulta de usuarios que gestione
referencias nulas de forma segura. Emplea operadores propios de Kotlin como
?., ?:, !! y let para controlar posibles valores nulos.*/

/* En Kotlin, puedes utilizar operadores de seguridad de
nulos para manejar los valores nulos de forma segura.
- Operador de llamada segura (?.): El operador de llamada
segura ?. te permite acceder a las propiedades de un
objeto nulo sin lanzar una excepción de referencia nula.
- Operador de elvis (?:): El operador de elvis ?: te permite
proporcionar un valor predeterminado en caso de que una
expresión sea nula.
- Operador de no nulo (!!): El operador de no nulo !! te
permite forzar la ejecución de una expresión nula, lo que
puede lanzar una excepción de referencia nula si la
expresión es nula.*/

fun main() {

    val nombre: String? = null

    // 1. Operador de llamada segura (?.)
    // Intenta obtener la longitud sin crashear si es nulo
    println("Longitud: ${nombre?.length}") // Devuelve null

    // 2. Operador Elvis (?:)
    // Devuelve el valor de la izquierda o el alternativo de la derecha si es nulo
    val usuarioFinal = nombre ?: "Invitado Anónimo"
    println("Hola, $usuarioFinal")

    // 3. Operador no nulo (!!)
    // Fuerza el valor, pero lanza un NullPointerException si es nulo
    try {
        val forzarValor: String = nombre!!
    } catch (e: NullPointerException) {
        println("¡Cuidado! Usar '!!' en un valor nulo provocó una excepción.")
    }

}