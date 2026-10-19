
/* Actividad: Implementa una función que clasifique a una persona en función de su edad.
Utiliza las estructuras when e if como expresiones que devuelvan un valor, evitando su
uso como sentencias tradicionales.*/

fun main() {
    println("Edad 5 años: " + clasificacionEdad(5))   // Salida: Infante
    println("Edad 15 años: " + clasificacionEdad(15))  // Salida: Adolescente
    println("Edad 30 años: " + clasificacionEdad(30))  // Salida: Adulto
    println("Edad -1 años: " + clasificacionEdad(-1))  // Salida: Edad no válida
}

fun clasificacionEdad(edad: Int): String {
    return when {
        edad < 0 -> "Edad no válida"
        edad in 0..11 -> "Infante"
        edad in 12..17 -> "Adolescente"
        else -> if (edad in 18..64) "Adulto" else "Adulto mayor"
    }
}