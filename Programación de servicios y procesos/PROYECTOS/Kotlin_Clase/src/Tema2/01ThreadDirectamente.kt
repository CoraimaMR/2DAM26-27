package Tema2

fun main() {
    val thread = Thread { //función lambda
        println("Hilo ejecutándose: ${Thread.currentThread().name}")
        Thread.sleep(1000)
        println("Hilo finalizado")
    }//end función lambda

    thread.start() // Inicia la ejecución del hilo
    println("Hilo principal sigue...")
    thread.join() // Espera a que el hilo termine
    println("Programa finalizado")
}