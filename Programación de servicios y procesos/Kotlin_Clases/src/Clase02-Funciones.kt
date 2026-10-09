
fun main() {

    // Funciones
    fun esPar2(parametro: Int): Boolean = parametro % 2 == 0;

    fun vacia(palabritas: String) = println(palabritas);

    vacia("Esta es la función vacía");

    fun unirVacias(function: (String) -> Unit, mundo: String): String {
        vacia("Hello world");
        return mundo;
    }

    //TODO: aclarar esto con los alumnos


    // Funciones lambda: Funciones dentro de funciones
    fun sumar(a: Int, b: Int): Int {
        return a + b;
    }

    fun utilizarSumar(a: Int, b: Int, funcion: (Int, Int) -> Int): Int {
        return funcion(a, b);
    }

    val resultado = utilizarSumar(2, 3, ::sumar);

    println(resultado)

    fun calcularPrecio(precio: Double, iva: Double = 0.21, descuento: Double = 0.0): Double {
        return precio * (1 + iva) - descuento;
    }

    println(calcularPrecio(100.0));
    println(calcularPrecio(100.0, 0.10));
    println(calcularPrecio(100.0, 0.10, 0.50));


    // Parámetros con valores por defecto
    fun saludar(nombre: String = "Mundo") {
        println("Hola, $nombre !")//$ con los string en las llamadas!
    }

    saludar()

}