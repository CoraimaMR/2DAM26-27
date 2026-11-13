package Tema1

fun main() {
    println("Iniciando proceso...")
    val process = ProcessBuilder("bash","-c","sleep", "2").start()
    process.waitFor()
    println("Proceso finalizado.")
}

