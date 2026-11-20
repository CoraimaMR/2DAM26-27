package Tema1

import java.lang.ProcessBuilder

fun main() {
    // Crear un proceso para ejecutar el comando 'sleep 10'
    val processBuilder = ProcessBuilder("bash", "-c", "sleep 10 ")
    try {
        val process = processBuilder.start() // Iniciar el proceso
        val processHandle = process.toHandle() // Obtener el ProcessHandle del proceso

        println("Proceso iniciado con PID: ${processHandle.pid()}")

        /*
        println("¿El proceso está vivo? ${processHandle.isAlive()}")
        val info = processHandle.info()
        println("Comando: ${info.command().orElse("desconocido")}")
        println("Usuario: ${info.user().orElse("desconocido")}")
        println("Inicio: ${info.startInstant().orElse(null)}")
         */

        // Esperar a que el proceso termine
        val exitCode = process.waitFor()
        println("Proceso terminado con código de salida: $exitCode")
    } catch (e: Exception) {
        e.printStackTrace()
    }

//ProcessHandle también permite consultar procesos que no hemos lanzado nosotros.
   /* val actual = ProcessHandle.current()
    println("PID de esta JVM: ${actual.pid()}")*/
// Por ejemplo, podemos obtener el proceso actual o recorrer todos los procesos del sistema:
    /*
     ProcessHandle.allProcesses()
        .filter { it.info().command().isPresent }
        .limit(10)
        .forEach { println("${it.pid()} -> ${it.info().command().get()}") }

     */
}