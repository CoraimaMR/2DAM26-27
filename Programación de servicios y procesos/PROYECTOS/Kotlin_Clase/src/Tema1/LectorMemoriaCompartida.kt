package Tema1

import java.nio.channels.FileChannel
import java.nio.file.Path
import java.nio.file.StandardOpenOption.READ

fun main() {
    val fichero = Path.of("src/Tema1/memoria_compartida.dat")

    FileChannel.open(fichero, READ).use { canal ->
        val memoria = canal.map(FileChannel.MapMode.READ_ONLY, 0, 1024)

        val longitud = memoria.getInt(0)
        val bytes = ByteArray(longitud)
        memoria.get(4, bytes)
        println("Leído de la memoria compartida: ${String(bytes)}")
    }
}