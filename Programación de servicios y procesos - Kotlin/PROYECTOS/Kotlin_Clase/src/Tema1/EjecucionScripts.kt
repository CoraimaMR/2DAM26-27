package Tema1

import java.io.File
import java.io.InputStreamReader
import java.io.BufferedReader
import java.lang.ProcessBuilder


fun main() {
    // Crear un proceso para ejecutar un script externo
    val processBuilder = ProcessBuilder("path/to/your/script.sh")
    /*
    Si queremos pasar algo como parámetro al script:
        val processBuilder = ProcessBuilder("path/to/your/script.sh", "arg1", "arg2")
     */
    processBuilder.directory(File("/path/to/your/script/directory")) // Establecer el directorio de trabajo
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