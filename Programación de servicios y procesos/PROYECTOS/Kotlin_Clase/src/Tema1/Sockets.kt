package Tema1

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

fun main() {
    // Iniciar un servidor de sockets
    val server = ServerSocket(9999)
    println("Esperando conexiones...")
    val client = server.accept()
    println("Cliente conectado: ${client.inetAddress}")

    // Leer datos del cliente
    val reader = BufferedReader(InputStreamReader(client.inputStream))
    var line: String?
    while (reader.readLine().also { line = it } != null) {
        println("Recibido del cliente: $line")
    }

    // Cerrar conexiones
    reader.close()
    client.close()
    server.close()
}