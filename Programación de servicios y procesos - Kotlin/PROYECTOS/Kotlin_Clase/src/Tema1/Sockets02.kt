package Tema1

import java.io.PrintWriter
import java.net.Socket

fun main() {
    val socket = Socket("localhost", 9999)

    val writer = PrintWriter(socket.getOutputStream(), true)
    writer.println("Hola")

    writer.close()
    socket.close()
}