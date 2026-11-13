package Tema1

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

fun main(){
    val processBuilder = ProcessBuilder("bash","-c","ls -l")
    //Establecer el directorio de trabajo
    processBuilder.directory(File("C:\\Users\\Usuario\\Desktop\\2DAM-AS-2627\\Kotlin_Practicas\\src\\Tema1"))
    // Redirigir errores al flujo de salida
    processBuilder.redirectErrorStream(true)
    try {

        // Iniciamos el proceso, start devuelve un objeto Process
        val process = processBuilder.start()

        //Leer la salida del proceso
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        var line:String?
        while(reader.readLine().also {line = it}!= null){
            println(line)
        }
        //Esperar a que el proceso finalice
        val exitCode = process.waitFor()
        println("Proceso terminado: $exitCode")
    }catch(e: Exception){
        e.printStackTrace()
    }
}
