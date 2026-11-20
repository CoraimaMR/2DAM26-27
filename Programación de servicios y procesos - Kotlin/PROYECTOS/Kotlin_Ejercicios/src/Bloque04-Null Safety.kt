/*Actividad
Desarrolla una aplicación de consulta de usuarios que gestione referencias nulas de forma segura.
Emplea los operadores propios de Kotlin ?., ?:, !! y let para controlar posibles valores nulos.*/

fun main() {

    val usuario: Usuario? = Usuario("Lucía", "lucia@example.com")
    val usuarioAusente: Usuario? = null

    val nombreUsuario = usuario?.nombre ?: "Usuario desconocido"
    val emailUsuario = usuarioAusente?.email ?: "No hay email disponible"

    println("Nombre del usuario: $nombreUsuario")
    println("Email del usuario ausente: $emailUsuario")

    usuario?.let {
        println("Consulta segura: ${it.nombre} (${it.email})")
    }

    if (usuario != null) {
        println("Acceso con !! tras comprobar el valor: ${usuario!!.nombre}")
    }
}

data class Usuario(val nombre: String, val email: String)