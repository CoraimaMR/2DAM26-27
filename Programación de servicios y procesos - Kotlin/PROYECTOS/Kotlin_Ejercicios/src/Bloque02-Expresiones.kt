/*Actividad
Implementa una función que clasifique a una persona en función de su edad.
Utiliza las estructuras when e if como expresiones que devuelvan un valor,
evitando su uso como sentencias tradicionales.*/

fun main() {
    val edadUsuario = 25
    
    // Uso de 'when' como expresión dentro de la función
    val categoria = clasificarEdad(edadUsuario)
    
    // Uso de 'if' como expresión para asignar un valor directamente a una variable
    val estadoLegal = if (edadUsuario >= 18) "Es mayor de edad" else "Es menor de edad"

    println("Edad: $edadUsuario años -> Clasificación: $categoria ($estadoLegal)")
}

fun clasificarEdad(edad: Int): String = when {
    edad < 0 -> "Edad no válida"
    edad in 0..11 -> "Niño/a"
    edad in 12..17 -> "Adolescente"
    edad in 18..64 -> "Adulto/a"
    else -> "Adulto/a mayor"
}