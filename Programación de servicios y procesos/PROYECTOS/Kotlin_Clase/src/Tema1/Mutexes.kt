package Tema1

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread
import kotlin.concurrent.withLock

fun main() {
    val lock = ReentrantLock()

    val tareas = (1..2).map { id ->
        thread {
            // withLock bloquea el mutex y lo desbloquea al salir del bloque
            lock.withLock {
                println("Proceso $id: Accediendo al recurso compartido")
                Thread.sleep(1000)
                println("Proceso $id: Liberando el recurso compartido")
            }
        }
    }
    tareas.forEach { it.join() }
}