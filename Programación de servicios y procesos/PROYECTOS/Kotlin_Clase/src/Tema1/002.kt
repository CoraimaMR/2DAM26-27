package Tema1

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val process = ProcessBuilder("cmd", "/c", "dir", "/q").start()
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    reader.lines().forEach { println(it) }
}


/*

import java.io.BufferedReader
import java.io.InputStreamReader

fun Poo.main() {
    // Todo lo que va tras -Command es la orden que interpreta PowerShell
    val process = ProcessBuilder("powershell", "-Command", "Get-ChildItem | Format-List").start()
    process.outputStream.close()
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    reader.lines().forEach { println(it) }
}

 */
/*
import java.io.BufferedReader
import java.io.InputStreamReader

fun Poo.main() {
    val process = ProcessBuilder("bash","-c","ls -l").start()
    val reader = BufferedReader(InputStreamReader(process.inputStream))
    reader.lines().forEach { println(it) }
}*/