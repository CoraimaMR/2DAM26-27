package Tema1

import java.util.concurrent.Semaphore
import kotlin.concurrent.thread

fun main() {
    val semaphore = Semaphore(1) // Semáforo binario

    val tareas = (1..2).map { id ->
        thread {
            semaphore.acquire()
            try {
                println("Proceso $id: Accediendo al recurso compartido")
                Thread.sleep(1000)
                println("Proceso $id: Liberando el recurso compartido")
            } finally {
                semaphore.release() // Liberar siempre, aunque haya una excepción
            }
        }
    }
    tareas.forEach { it.join() }
}