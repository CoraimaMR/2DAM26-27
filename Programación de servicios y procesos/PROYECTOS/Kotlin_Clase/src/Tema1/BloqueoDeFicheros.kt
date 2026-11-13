package Tema1

import java.nio.channels.FileChannel
import java.nio.file.Path
import java.nio.file.StandardOpenOption.*

fun main() {
    val pid = ProcessHandle.current().pid()
    val fichero = Path.of("src/Tema1/registro_compartido.txt")

    FileChannel.open(fichero, CREATE, WRITE, APPEND).use { canal ->
        println("[$pid] Esperando el bloqueo del fichero...")
        canal.lock().use { // Bloquea hasta que ningún otro proceso tenga el lock
            println("[$pid] Bloqueo obtenido, escribiendo...")
            canal.write(java.nio.ByteBuffer.wrap("Línea escrita por el proceso $pid\n".toByteArray()))
            Thread.sleep(3000) // Simula una escritura larga
        } // El bloqueo se libera al salir del bloque
        println("[$pid] Bloqueo liberado")
    }
}