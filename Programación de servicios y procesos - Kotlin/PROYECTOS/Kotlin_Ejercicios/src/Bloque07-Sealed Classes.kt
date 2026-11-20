/*Actividad
Construye un modelo para representar los distintos estados de una conexión de red mediante sealed classes.
Gestiona cada posible estado utilizando una estructura when que contemple todos los casos existentes.*/

fun main() {

    val estados: List<EstadoConexion> = listOf(
        EstadoConexion.Desconectado,
        EstadoConexion.Conectando(65),
        EstadoConexion.Conectado,
        EstadoConexion.Error("No se pudo encontrar la red")
    )

    estados.forEach { estado ->
        println(describirEstado(estado))
    }
}

sealed class EstadoConexion {
    object Desconectado : EstadoConexion()
    object Conectado : EstadoConexion()
    data class Conectando(val progreso: Int) : EstadoConexion()
    data class Error(val mensaje: String) : EstadoConexion()
}

fun describirEstado(estado: EstadoConexion): String {
    return when (estado) {
        EstadoConexion.Desconectado -> "Sin conexión"
        EstadoConexion.Conectado -> "Conexión establecida"
        is EstadoConexion.Conectando -> "Conectando: ${estado.progreso}%"
        is EstadoConexion.Error -> "Error de conexión: ${estado.mensaje}"
    }
}