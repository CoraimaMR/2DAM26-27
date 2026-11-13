package Tema1

import kotlin.concurrent.thread

fun main() {
    val comandos = listOf("1", "2", "3")
    val hilos = comandos.map { segundos ->
        thread {
            val process = ProcessBuilder("bash","-c","sleep", segundos).start()
            process.waitFor()
            println("Proceso sleep $segundos terminado")
        }
    }
    hilos.forEach { it.join() }
}