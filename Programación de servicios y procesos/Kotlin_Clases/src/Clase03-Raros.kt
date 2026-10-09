fun main() {

    // La palabra reservada it:  it se utiliza para referirse al único parámetro de entrada de la función lambda

    val triple: (Int)->Int = {it*3}
    println(triple(2))

    val numeros3 = listOf(1,2,3,4,5)

    val cuadrados = numeros3.map {it * 2}
    // 2,4,6,8,10

    val nombres = listOf("Mónica", "Fermin", "Rafael", "Jorge", "Coraima")
    val masDe4 = nombres.filter {
        it.length > 4
    }
    println(masDe4)

    // Número variable de argumentos: Vararg

    fun mayor (vararg numeros: Int): Int {
        return numeros.max()
    }
    println(mayor(2,8,3,2,65,2,35,2))

    fun media(vararg numeros: Int): Double {
        return numeros.average()
    }
    println(media(2,8))

    fun calcular (vararg numCalcular: Int, operacion:(Int)-> Int): List<Int> {
        return numCalcular.map {
            operacion(it)
        }
    }
    val resuladoCalcular = calcular(1,2,3,4,5, operacion = {it*2})
    println(resuladoCalcular)

    // Funciones de extensión

    //OPC 1
    fun invertirYmayuscula(mensaje: String): String {
        return mensaje.reversed().uppercase()
    }

    val texto = "Hola, mundo!"
    val textoInvertidoYmayuscula = invertirYmayuscula( mensaje = texto)

    // OPC 2
    fun String.invertirYmayuscula(): String {
        return this.reversed().uppercase()
    }

    val texto2 = "Hola, mundo!"
    val textoInvertidoYmayuscula2 = texto.invertirYmayuscula()

    // Null safety en Kotlin

    var arbol: String? = null
    arbol?.length
//    arbol = "cedro"
    var tamaArbol = arbol?.length?:0
    println(tamaArbol)

    fun String?.oLongitud(): Int {
        return this?.length ?: 0
    }
    val nombre: String? = null
    val longitud = nombre.oLongitud() // 0

}