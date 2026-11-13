package Tema1

fun main() {
    val process = ProcessBuilder("bash","-c","sleep 5")
        .redirectOutput(ProcessBuilder.Redirect.DISCARD) // Descartar la salida
        .redirectError(ProcessBuilder.Redirect.DISCARD)  // Descartar los errores
        .start()

    println("Proceso iniciado en segundo plano con PID: ${process.pid()}")

    // Registrar qué hacer cuando termine, sin bloquear al padre
    process.onExit().thenAccept { p ->
        println("El proceso ${p.pid()} ha terminado con código ${p.exitValue()}")
    }

    // Mientras tanto, el programa padre sigue trabajando
    repeat(3) { i ->
        println("El padre sigue trabajando... ($i)")
        Thread.sleep(3000)
    }

    process.waitFor() // Solo para que el ejemplo no termine antes que el hijo
}