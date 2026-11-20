/*Actividad
Desarrolla una herramienta para configurar perfiles de usuario haciendo uso de las distintas
scope functions de Kotlin (let, run, with, apply y also), aplicando cada una en el contexto más adecuado.*/

fun main() {

    val perfil = PerfilUsuario("Marta", 24, "Madrid", false).apply {
        ciudad = "Valencia"
        verificado = true
    }.also {
        println("Perfil configurado: $it")
    }

    val resumen = perfil.let {
        "${it.nombre}, ${it.edad} años, ${it.ciudad}"
    }
    println("Resumen creado con let: $resumen")

    val estado = perfil.run {
        if (verificado) "verificado" else "pendiente de verificación"
    }
    println("Estado calculado con run: $estado")

    with(perfil) {
        println("Perfil mostrado con with: $nombre vive en $ciudad.")
    }
}

data class PerfilUsuario(
    val nombre: String,
    val edad: Int,
    var ciudad: String,
    var verificado: Boolean
)