package Tema1

import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread
import kotlin.concurrent.withLock

/*
En este ejemplo, la clase Monitor encapsula un recurso compartido y
 proporciona métodos para acceder a él de manera segura.
 El primero que llama a accederRecurso() lo ocupa;
 el segundo se queda esperando en condition.await()
 (lo que libera temporalmente el lock)
 hasta que el primero llama a liberarRecurso()
 y le avisa con condition.signal().
 */


class Monitor {
    private val lock = ReentrantLock()
    private val condition: Condition = lock.newCondition()
    private var recursoDisponible = true // Al principio el recurso está libre

    fun accederRecurso() {
        lock.withLock {
            while (!recursoDisponible) {
                condition.await()
            }
            println("Accediendo al recurso compartido")
            recursoDisponible = false
        }
    }

    fun liberarRecurso() {
        lock.withLock {
            println("Liberando el recurso compartido")
            recursoDisponible = true
            condition.signal()
        }
    }
}

fun main() {
    val monitor = Monitor()

    val tareas = (1..2).map {
        thread {
            monitor.accederRecurso()
            Thread.sleep(1000)
            monitor.liberarRecurso()
        }
    }
    tareas.forEach { it.join() }
}