package Tema1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.concurrent.thread


/*
En este ejemplo, la clase Counter tiene un mét0do increment que incrementa
 un contador de manera segura utilizando la palabra clave @Synchronized.
 La prueba testConcurrentIncrement crea 100 hilos, cada uno incrementando
 el contador 1000 veces. Al final, se verifica que el valor del contador sea
 100000, asegurando que la concurrencia se maneje correctamente.
 */

class Counter {
    private var count = 0

    @Synchronized
    fun increment() {
        count++
    }

    fun getCount(): Int {
        return count
    }
}

class CounterTest {

    @Test
    fun testConcurrentIncrement() {
        val counter = Counter()
        val threads = List(100) {
            thread {
                repeat(1000) {
                    counter.increment()
                }
            }
        }
        threads.forEach { it.join() }
        assertEquals(100000, counter.getCount())
    }
}