package Tema1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.ProcessBuilder

fun main() {
    // Crear un proceso para ejecutar el comando 'grep "kotlin"'
    val processBuilder = ProcessBuilder("bash", "-c","grep kotlin")
    val process = processBuilder.start()

    // Escribir en el pipe de entrada del proceso
    val writer = OutputStreamWriter(process.outputStream)
    writer.write("kotlin es un lenguaje de programación\n")
    writer.write("java es otro lenguaje de programación\n")
    writer.flush()
    writer.close()

    // Leer la salida del proceso
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    var line: String?
    while (reader.readLine().also { line = it } != null) {
        println(line)
    }

    // Esperar a que el proceso termine
    val exitCode = process.waitFor()
    println("Proceso terminado con código de salida: $exitCode")
}