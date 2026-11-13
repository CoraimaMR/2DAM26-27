package Tema1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/*
En este ejemplo, tenemos dos procesos simulados, ProcessA y ProcessB.
La prueba de integración testProcessCommunication verifica que ProcessA
pueda enviar un mensaje a ProcessB y que ProcessB lo reciba correctamente.
 */
class IntegrationTest {

    @Test
    fun testProcessCommunication() {
        val processA = ProcessA()
        val processB = ProcessB()

        processA.start()
        processB.start()

        processA.sendMessage("Hello from A")
        assertEquals("Hello from A", processB.receiveMessage())
    }
}
class ProcessA {
    private var message: String? = null

    fun start() {
        // Simulación de inicio del proceso
    }

    fun sendMessage(msg: String) {
        message = msg
    }
}
class ProcessB {
    private var message: String? = null

    fun start() {
        // Simulación de inicio del proceso
    }

    fun receiveMessage(): String? {
        return message
    }
}