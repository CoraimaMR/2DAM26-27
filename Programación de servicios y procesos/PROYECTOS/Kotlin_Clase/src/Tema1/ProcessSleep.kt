package Tema1

import java.io.File
import java.io.InputStreamReader
import java.io.BufferedReader
import java.lang.ProcessBuilder
import java.util.concurrent.TimeUnit

fun main() {
    // Crear un proceso para ejecutar el comando 'sleep'
    //Sleep sirve para crear un proceso que se ejecute en segundo plano
    val processBuilder = ProcessBuilder("bash","-c","sleep 10 ")
    processBuilder.redirectErrorStream(true) // Redirigir errores al flujo de salida

    try {
        val process = processBuilder.start() // Iniciar el proceso

        // Leer la salida del proceso
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            println(line)
        }

        // Esperar a que el proceso termine
        val exitCode = process.waitFor()
        println("Proceso terminado con código de salida: $exitCode")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
