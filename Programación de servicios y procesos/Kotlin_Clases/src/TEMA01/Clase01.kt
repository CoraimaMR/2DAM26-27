package TEMA01

import java.io.BufferedReader
import java.io.InputStreamReader

//fun main() {
//    val process = ProcessBuilder("bash", "-c", "cd / && ls").start()
//    val reader = BufferedReader(InputStreamReader(process.inputStream))
//    reader.lines().forEach { println(it) }
//}

fun main() {
    // Forzamos la ruta exacta del ejecutable para saltarnos fallos de caché del PATH
    val process = ProcessBuilder(
        "C:\\Program Files\\Git\\bin\\bash.exe",
        "-c",
        "cd / && ls"
    ).start()

    // Leemos la respuesta estándar del comando
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    reader.lines().forEach { println(it) }

    // Leemos también los posibles errores por si Git Bash arroja alguna alerta
    val errorReader = BufferedReader(InputStreamReader(process.errorStream))
    errorReader.lines().forEach { System.err.println("Error de Bash: $it") }

    process.waitFor()
}
