package Tema1

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val process = ProcessBuilder("bash","-c","ps").start()
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    reader.lines().forEach { println(it) }
}
