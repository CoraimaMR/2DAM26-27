/*Actividad
Crea una aplicación de gestión bancaria utilizando herencia.
Define una clase principal llamada Cuenta y extiéndela mediante las clases CuentaAhorro
y CuentaCorriente, incorporando características y comportamientos específicos para cada una.*/

fun main() {

    val cuentaAhorro = CuentaAhorro("Ana", 1000.0, 0.02)
    val cuentaCorriente = CuentaCorriente("Luis", 500.0, 300.0)

    cuentaAhorro.ingresar(200.0)
    cuentaAhorro.aplicarInteres()
    println("Saldo de ahorro de ${cuentaAhorro.titular}: ${cuentaAhorro.saldo} €")

    val retiradaCorrecta = cuentaCorriente.retirar(650.0)
    println("Retirada de 650 € permitida: $retiradaCorrecta")
    println("Saldo de cuenta corriente de ${cuentaCorriente.titular}: ${cuentaCorriente.saldo} €")

    val retiradaExcesiva = cuentaCorriente.retirar(200.0)
    println("Retirada adicional de 200 € permitida: $retiradaExcesiva")
}

open class Cuenta(val titular: String, var saldo: Double) {
    fun ingresar(cantidad: Double) {
        if (cantidad > 0) {
            saldo += cantidad
        }
    }

    open fun retirar(cantidad: Double): Boolean {
        if (cantidad <= 0 || cantidad > saldo) {
            return false
        }
        saldo -= cantidad
        return true
    }
}

class CuentaAhorro(
    titular: String,
    saldo: Double,
    private val interesAnual: Double
) : Cuenta(titular, saldo) {
    fun aplicarInteres() {
        saldo += saldo * interesAnual
    }
}

class CuentaCorriente(
    titular: String,
    saldo: Double,
    private val limiteDescubierto: Double
) : Cuenta(titular, saldo) {
    override fun retirar(cantidad: Double): Boolean {
        if (cantidad <= 0 || cantidad > saldo + limiteDescubierto) {
            return false
        }
        saldo -= cantidad
        return true
    }
}