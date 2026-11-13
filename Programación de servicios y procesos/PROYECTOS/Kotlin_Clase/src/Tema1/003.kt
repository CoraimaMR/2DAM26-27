package Tema1

//Lanzamos el comando a través del intérprete porque,
// si se pasa directamente a ProcessBuilder, start()
// lanza una IOException (el ejecutable no existe)
// y no llega a haber ninguna salida de error que leer.
// En cambio, el intérprete sí arranca y es él quien escribe
// el error en su salida de error.


import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val pb = ProcessBuilder("bash", "-c", "comando_inexistente")
    pb.redirectErrorStream(false)
    val process = pb.start()
    val stdOut = BufferedReader(InputStreamReader(process.inputStream))
    val stdErr = BufferedReader(InputStreamReader(process.errorStream))
    println("Salida estándar:")
    stdOut.lines().forEach { println(it) }
    println("Salida de error:")
    stdErr.lines().forEach { println(it) }
}
