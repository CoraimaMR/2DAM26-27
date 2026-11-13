package Tema1

fun main() {
    val process = ProcessBuilder("bash","-c", "ping -c 3 localhost")
        .inheritIO() // El hijo escribe directamente en la consola del padre
        .start()

    //Si da fallo en Windows en Socket: Entrar en WSL y poner:
    // getcap $(which ping)
    // sudo setcap cap_net_raw+ep /usr/bin/ping

    println("Proceso iniciado con PID: ${process.pid()}")

    // El padre se bloquea hasta que el hijo termine
    val exitCode = process.waitFor()
    println("Proceso terminado con código de salida: $exitCode")
}