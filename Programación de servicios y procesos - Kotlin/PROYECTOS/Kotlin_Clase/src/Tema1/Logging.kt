package Tema1

import java.util.logging.FileHandler
import java.util.logging.Logger
import java.util.logging.SimpleFormatter
import kotlin.concurrent.thread

fun main() {
    val logger = Logger.getLogger("MyLogger")
    val fileHandler = FileHandler("app.log", true)
    fileHandler.formatter = SimpleFormatter()
    logger.addHandler(fileHandler)

    // Simulación de procesos concurrentes
    repeat(5) { i ->
        thread {
            logger.info("Iniciando proceso $i")
            Thread.sleep((1000..3000).random().toLong())
            logger.info("Finalizando proceso $i")
        }
    }
}