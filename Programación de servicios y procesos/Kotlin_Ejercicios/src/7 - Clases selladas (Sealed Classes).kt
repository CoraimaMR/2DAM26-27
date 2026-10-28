
/*Actividad: Construye un modelo para representar los distintos
estados de una conexión de red mediante sealed classes. Gestiona
cada posible estado utilizando una estructura when que contemple
todos los casos existentes.*/

/*Las clases selladas son un tipo especial de clase en Kotlin que se utilizan para
representar un conjunto finito de subclases.
Las clases selladas son útiles cuando tienes un conjunto limitado de subclases y
quieres asegurarte de que todas las subclases se manejan de forma segura en
un bloque when.
Declaración
- Puedes declarar una clase sellada
utilizando la palabra clave sealed
antes de la palabra clave class.

sealed class Resultado {
data class Exito(val mensaje: String) : Resultado()
data class Error(val mensaje: String) : Resultado()
}

Uso:
- Las clases selladas se utilizan
principalmente en expresiones
when para manejar de forma
segura todas las subclases.

fun procesarResultado(resultado: Resultado) {
when (resultado) {
is Resultado.Exito-> println("Exito: ${resultado.mensaje}")
is Resultado.Error-> println("Error: ${resultado.mensaje}")
}
}*/

fun main() {
    println("=== GESTIÓN DE ESTADOS DE RED CON SEALED CLASSES ===\n")

    // Simulando diferentes estados de la conexión
    val estado1: EstadoConexion = EstadoConexion.Desconectado
    val estado2: EstadoConexion = EstadoConexion.Conectando
    val estado3: EstadoConexion = EstadoConexion.Conectado("100 Mbps")
    val estado4: EstadoConexion = EstadoConexion.Error(404, "No se encontró el servidor")

    gestionarEstado(estado1)
    gestionarEstado(estado2)
    gestionarEstado(estado3)
    gestionarEstado(estado4)
}

// 1. Clase sellada para representar los estados de una conexión de red
sealed class EstadoConexion {
    object Desconectado : EstadoConexion()
    object Conectando : EstadoConexion()
    data class Conectado(val velocidad: String) : EstadoConexion()
    data class Error(val codigo: Int, val mensaje: String) : EstadoConexion()
}

// 2. Función que gestiona todos los estados de manera segura y exhaustiva usando 'when'
fun gestionarEstado(estado: EstadoConexion) {
    when (estado) {
        is EstadoConexion.Desconectado -> println("Estado: La red está desconectada.")
        is EstadoConexion.Conectando -> println("Estado: Estableciendo conexión...")
        is EstadoConexion.Conectado -> println("Estado: Conectado exitosamente. Velocidad: ${estado.velocidad}")
        is EstadoConexion.Error -> println("Estado: Error ${estado.codigo} - ${estado.mensaje}")
    }
}