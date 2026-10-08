
/* Ejercicio 6. Actualización de datos
Crea variables para gestionar una cuenta bancaria:
Titular (String)
Saldo (Double)
Realiza las siguientes operaciones:
1.Ingreso de 300 €.
2.Retirada de 150 €.
3.Mostrar el saldo final. */

fun main() {

    val titular: String = "Coraima"
    var saldo: Double = 0.0

    saldo = saldo + 300
    saldo = saldo - 150

    println("\n--- Datos finales ---")
    println("Titular: $titular")
    println("Saldo: $saldo €")

}