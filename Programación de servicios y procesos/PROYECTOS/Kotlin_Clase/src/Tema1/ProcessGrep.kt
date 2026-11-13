package Tema1

import java.io.File
import java.io.InputStreamReader
import java.io.BufferedReader
import java.lang.ProcessBuilder
import java.util.concurrent.TimeUnit

fun main() {
    // Crear un proceso para ejecutar el comando 'grep'
    //Grep sirve para buscar una cadena de texto en un archivo
    val processBuilder = ProcessBuilder("bash","-c","grep cadena_a_buscar archivo.txt")
    processBuilder.directory(File("C:\\Users\\Usuario\\Desktop\\2DAM-AS-2627\\Kotlin_Practicas\\src\\Tema1"))
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
