package Tema1

import java.io.BufferedReader
import java.io.InputStreamReader


fun main() {
    val process = ProcessBuilder("bash","-c","cd / && ls").start()
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    reader.lines().forEach { println(it) }
}

/*
fun Poo.main() {
    // Get-ChildItem es un cmdlet: se ejecuta dentro de powershell
    val process = ProcessBuilder("powershell", "-Command", "Get-ChildItem").start()
    process.outputStream.close() // Cerramos la entrada para que PowerShell no espere datos
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    reader.lines().forEach { println(it) }
}
*/



/*
fun Poo.main() {
    // dir es un comando interno de CMD: se ejecuta dentro de cmd
    val process = ProcessBuilder("cmd", "c/", "dir").start()
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    reader.lines().forEach { println(it) }
}
*/