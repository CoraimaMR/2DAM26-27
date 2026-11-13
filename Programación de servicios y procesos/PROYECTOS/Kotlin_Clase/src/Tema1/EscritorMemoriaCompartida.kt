package Tema1

import java.nio.channels.FileChannel
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption.*

fun main() {
    val fichero = Path.of("src/Tema1/memoria_compartida.dat")

    FileChannel.open(fichero, CREATE, READ, WRITE).use { canal ->
        // Mapear 1024 bytes del fichero en memoria
        val memoria = canal.map(FileChannel.MapMode.READ_WRITE, 0, 1024)
        val mensaje = "Hola desde la memoria compartida".toByteArray()
        memoria.putInt(0, mensaje.size)     // Primero la longitud del mensaje
        memoria.put(4, mensaje)             // Después el contenido
        println("Mensaje escrito en la memoria compartida")
    }
}