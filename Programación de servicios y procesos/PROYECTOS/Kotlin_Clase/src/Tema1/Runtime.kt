package Tema1

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    try {
        val process = Runtime.getRuntime().exec(arrayOf("bash","-c","ls", "-l"))
        //La versión Runtime.exec(String), que recibe el comando completo en una sola cadena,
        // está marcada como obsoleta (deprecated) desde Java 18,
        // porque divide el texto por espacios y falla con rutas o argumentos que los contienen.
        // Hay que usar la versión que recibe un array (exec(arrayOf("ls", "-l"))) o,
        // mejor aún, ProcessBuilder.
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            println(line)
        }
        val exitCode = process.waitFor()
        println("Proceso terminado con código de salida: $exitCode")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}