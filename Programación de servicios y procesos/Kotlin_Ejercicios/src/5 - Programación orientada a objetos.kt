
/*Actividad: Crea una aplicación de gestión bancaria utilizando herencia.
Define una clase principal llamada Cuenta y extiéndela mediante las clases
 CuentaAhorro y CuentaCorriente, incorporando características y
 comportamientos específicos para cada una.*/

fun main() {
    println("=== PRUEBA DE CUENTA DE AHORRO ===")
    val cuentaAhorro = CuentaAhorro("Ana", 1000.0, 0.05)
    cuentaAhorro.depositar(200.0)
    cuentaAhorro.aplicarInteres()

    println("\n=== PRUEBA DE CUENTA CORRIENTE ===")
    val cuentaCorriente = CuentaCorriente("Carlos", 100.0, 150.0)
    cuentaCorriente.retirar(200.0)
    cuentaCorriente.retirar(200.0)
}

// 1. Clase base con atributos declarados en el cuerpo
open class Cuenta {
    var titular: String
    var saldo: Double

    constructor(titular: String, saldo: Double) {
        this.titular = titular
        this.saldo = saldo
    }

    open fun depositar(cantidad: Double) {
        saldo += cantidad
        println("[$titular] Depósito: +$cantidad. Saldo: $saldo")
    }

    open fun retirar(cantidad: Double) {
        if (saldo >= cantidad) {
            saldo -= cantidad
            println("[$titular] Retiro: -$cantidad. Saldo: $saldo")
        } else {
            println("[$titular] Fondos insuficientes para retirar $cantidad.")
        }
    }
}

// 2. Subclase CuentaAhorro con atributo fuera del constructor principal
class CuentaAhorro : Cuenta {
    val tasaInteres: Double

    constructor(titular: String, saldo: Double, tasaInteres: Double) : super(titular, saldo) {
        this.tasaInteres = tasaInteres
    }

    fun aplicarInteres() {
        val interes = saldo * tasaInteres
        saldo += interes
        println("[$titular] Interés aplicado: +$interes. Saldo: $saldo")
    }
}

// 3. Subclase CuentaCorriente con atributo fuera del constructor principal
class CuentaCorriente : Cuenta {
    val limiteSobregiro: Double

    constructor(titular: String, saldo: Double, limiteSobregiro: Double) : super(titular, saldo) {
        this.limiteSobregiro = limiteSobregiro
    }

    override fun retirar(cantidad: Double) {
        if (saldo + limiteSobregiro >= cantidad) {
            saldo -= cantidad
            println("[$titular] Retiro con sobregiro: -$cantidad. Saldo: $saldo")
        } else {
            println("[$titular] Límite de sobregiro excedido.")
        }
    }
}