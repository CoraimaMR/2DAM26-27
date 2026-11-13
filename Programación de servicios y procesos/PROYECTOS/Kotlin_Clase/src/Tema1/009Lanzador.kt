package Tema1

fun main() {
    // El proceso hijo es una nueva JVM que ejecuta hijo.jar
    val process = ProcessBuilder("java", "-jar", "src/Tema1/hijo.jar")
        .redirectErrorStream(true)
        .start()

    process.inputStream.bufferedReader().lines().forEach { println("[hijo]: $it") }
    println("El hijo terminó con código ${process.waitFor()}")
}