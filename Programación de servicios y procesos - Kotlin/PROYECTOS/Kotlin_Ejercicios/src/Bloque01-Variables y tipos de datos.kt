fun main() {
    /*Ejercicio 1. Datos personales
    Crea un programa que almacene la siguiente información de una persona:
    - Nombre (String)
    - Edad (Int)
    - Altura en metros (Double)
    - ¿Es estudiante? (Boolean)
    Muestra todos los datos por pantalla utilizando variables declaradas con val.
    Ampliación: Cambia algunas variables a var y modifica sus valores antes de mostrarlos.*/
    println("\n------------ EJERCICIO 01 ------------")

    // Versión inicial con val
    val nombre: String = "Elena"
    val edad: Int = 21
    val altura: Double = 1.68
    val esEstudiante: Boolean = true

    println("Nombre: $nombre, Edad: $edad, Altura: $altura m, ¿Es estudiante?: $esEstudiante")

    // Ampliación usando var y modificando valores
    var edadModificable: Int = 21
    var esEstudianteModificable: Boolean = true

    edadModificable = 22
    esEstudianteModificable = false

    println("Actualizado -> Nombre: $nombre, Edad: $edadModificable, Altura: $altura m, ¿Es estudiante?: $esEstudianteModificable")

    /*Ejercicio 2. Uso de var y val
    Declara las siguientes variables:
    - Nombre de una asignatura.
    - Número de alumnos matriculados.
    - Nota media de la clase.
    Utiliza val para los datos que no cambian y var para los que sí pueden cambiar.
    Después, incrementa en 3 el número de alumnos, actualiza la nota media y muestra los resultados.*/
    println("\n------------ EJERCICIO 02 ------------")

    val nombreAsignatura: String = "Programación en Kotlin"
    var alumnosMatriculados: Int = 28
    var notaMedia: Double = 7.4

    // Modificamos los datos variables
    alumnosMatriculados += 3
    notaMedia = 7.85

    println("Asignatura: $nombreAsignatura")
    println("Alumnos matriculados: $alumnosMatriculados")
    println("Nota media de la clase: $notaMedia")

    /*Ejercicio 3. Inferencia de tipos
    Declara las siguientes variables sin indicar el tipo explícitamente:
    val ciudad = "Madrid"
    val habitantes = 3200000
    val temperatura = 24.5
    val llueve = false
    Identifica qué tipo de dato ha inferido Kotlin para cada variable y muestra los valores por pantalla.*/
    println("\n------------ EJERCICIO 03 ------------")

    val ciudad = "Madrid"       // Tipo inferido: String
    val habitantes = 3200000    // Tipo inferido: Int
    val temperatura = 24.5      // Tipo inferido: Double
    val llueve = false          // Tipo inferido: Boolean

    println("Ciudad (String): $ciudad")
    println("Habitantes (Int): $habitantes")
    println("Temperatura (Double): $temperatura")
    println("¿Llueve? (Boolean): $llueve")

    /*Ejercicio 4. Conversión de tipos numéricos
    Crea una variable entera con el valor 25 y realiza las conversiones Int a Double e Int a String.
    Muestra el valor original, el valor convertido y el tipo de cada variable.
    Ayuda: Utiliza las funciones toDouble() y toString().*/
    println("\n------------ EJERCICIO 04 ------------")

    val numeroOriginal: Int = 25
    val numeroDouble: Double = numeroOriginal.toDouble()
    val numeroString: String = numeroOriginal.toString()

    println("Original -> Valor: $numeroOriginal | Tipo: ${numeroOriginal::class.simpleName}")
    println("A Double -> Valor: $numeroDouble | Tipo: ${numeroDouble::class.simpleName}")
    println("A String -> Valor: \"$numeroString\" | Tipo: ${numeroString::class.simpleName}")

    /*Ejercicio 5. Conversión de String a número
    Declara val edadTexto = "18", convierte el contenido a tipo Int y calcula la edad dentro de 5 años.
    Muestra todos los resultados.*/
    println("\n------------ EJERCICIO 05 ------------")

    val edadTexto = "18"
    val edadNumero: Int = edadTexto.toInt()
    val edadEnCincoAnos: Int = edadNumero + 5

    println("Edad en texto: \"$edadTexto\"")
    println("Edad como número (Int): $edadNumero")
    println("Edad dentro de 5 años: $edadEnCincoAnos")

    /*Ejercicio 6. Actualización de datos
    Crea variables para gestionar una cuenta bancaria: titular (String) y saldo (Double).
    Realiza un ingreso de 300 €, una retirada de 150 € y muestra el saldo final.*/
    println("\n------------ EJERCICIO 06 ------------")

    val titular: String = "Carlos Navarro"
    var saldo: Double = 1200.0

    // Ingreso de 300 €
    saldo += 300.0
    // Retirada de 150 €
    saldo -= 150.0

    println("Titular: $titular")
    println("Saldo final: $saldo €")

    /*Ejercicio 7. Comparación lógica
    Declara val numero1 = 15 y val numero2 = 20.
    Crea una variable booleana que indique si numero1 es mayor que numero2 y muestra el resultado.*/
    println("\n------------ EJERCICIO 07 ------------")

    val numero1 = 15
    val numero2 = 20

    val esMayor: Boolean = numero1 > numero2
    println("¿Es $numero1 mayor que $numero2? $esMayor")

    /*Ejercicio 8. Reto completo
    Desarrolla un programa para gestionar un videojuego con el nombre del jugador, nivel actual,
    puntuación y si tiene suscripción premium.
    Muestra los datos iniciales, incrementa el nivel en 1, aumenta la puntuación en 500 puntos,
    convierte la puntuación a String y muestra los datos actualizados.*/
    println("\n------------ EJERCICIO 08 ------------")

    val nombreJugador: String = "NovaKnight"
    var nivelActual: Int = 1
    var puntuacion: Int = 1250
    val tieneSuscripcionPremium: Boolean = true

    println("--- Datos iniciales ---")
    println("Jugador: $nombreJugador | Nivel: $nivelActual | Puntuación: $puntuacion | Premium: $tieneSuscripcionPremium")

    nivelActual += 1
    puntuacion += 500
    val puntuacionTexto: String = puntuacion.toString()

    println("--- Datos actualizados ---")
    println("Jugador: $nombreJugador | Nivel: $nivelActual | Puntuación (String): $puntuacionTexto | Premium: $tieneSuscripcionPremium")

}