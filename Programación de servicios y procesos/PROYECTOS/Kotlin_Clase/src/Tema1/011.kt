package Tema1
import java.io.File

fun main() {

    // ==========================================================
    // PASO 1: Crear un archivo temporal
    // ==========================================================

    val archivoTemporal = File.createTempFile("practica",".txt")
    println("Archivo temporal creado:")
    println(archivoTemporal.absolutePath)
    println()

    // ==========================================================
    // PASO 2: Escribir contenido usando printf desde bash
    // ==========================================================
    // printf enviará varias líneas al archivo temporal.
    //
    // > redirige la salida al archivo.
    //
    val escribir = ProcessBuilder(
        "bash",
        "-c",
        "printf 'Línea 1\nLínea 2\nLínea 3\nLínea 4\n' > ${archivoTemporal.absolutePath}"
    ).start()

    escribir.waitFor()

    // ==========================================================
    // PASO 3: Contar líneas usando wc -l
    // ==========================================================
    val contar = ProcessBuilder(
        "bash",
        "-c",
        "wc -l < ${archivoTemporal.absolutePath}"
    ).start()

    val numeroLineas = contar.inputStream
        .bufferedReader()
        .readText()
        .trim()

    contar.waitFor()

    println("Número de líneas: $numeroLineas")
    println()

    // ==========================================================
    // PASO 4: Leer contenido usando cat
    // ==========================================================
    val leer = ProcessBuilder(
        "bash",
        "-c",
        "cat ${archivoTemporal.absolutePath}"
    ).start()

    val contenido = leer.inputStream
        .bufferedReader()
        .readText()

    leer.waitFor()

    // ==========================================================
    // PASO 5: Mostrar contenido
    // ==========================================================
    println("Contenido del archivo:")
    println("----------------------")
    println(contenido)

    // ==========================================================
    // PASO 6: Eliminar archivo temporal
    // ==========================================================
    archivoTemporal.deleteOnExit()
}