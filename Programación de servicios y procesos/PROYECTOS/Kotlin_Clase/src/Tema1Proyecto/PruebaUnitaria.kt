package Tema1Proyecto

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class SolTest {

    @Test
    fun testComandar() {

        val proceso = ProcessBuilder(
            "java",
            "-cp",
            System.getProperty("java.class.path"),
            "Tema1Proyecto.MainKt",//cambiar aquí la clase de prueba
            "comandar",
            "src/Tema1Proyecto/ordenes.txt",
            "3"
        ).start()

        val salida =
            proceso.inputStream
                .bufferedReader()
                .readText()

        proceso.waitFor()

        println(salida)

        assertEquals(0, proceso.exitValue())
    }
}
