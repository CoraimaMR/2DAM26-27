package Tema1

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val echo = ProcessBuilder("bash", "-c", "echo hola").start()
    val tr = ProcessBuilder("bash", "-c","tr a-z A-Z").start()
    echo.inputStream.transferTo(tr.outputStream)
    tr.outputStream.close()
    val reader = BufferedReader(InputStreamReader(tr.inputStream))
    reader.lines().forEach { println(it) }
}