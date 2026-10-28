
/*Actividad: Desarrolla una herramienta para configurar perfiles
de usuario haciendo uso de las distintas scope functions de Kotlin
(let, run, with, apply y also), aplicando cada una en el contexto
más adecuado.*/

/*En Kotlin, los objetos genéricos te permiten escribir código que es reutilizable y
flexible, ya que puedes definir clases y funciones que trabajen con cualquier tipo
de datos. Puedes utilizar genéricos en clases, funciones e interfaces para crear
código genérico y flexible.
Los scope functions son funciones que te permiten
ejecutar un bloque de código en el contexto de un
objeto. Estas funciones te permiten acceder a las
propiedades y métodos del objeto de forma más
concisa y legible.
- Let: permite ejecutar un bloque de código en el
contexto de un objeto y devolver el resultado de la
última expresión del bloque.
- Run: A diferencia de let, la función run te
permite acceder a las propiedades y
métodos del objeto sin necesidad de
utilizar it.
- With: La función with es similar a run,
pero se utiliza con un objeto como
argumento en lugar de un receptor.
- Apply: A diferencia de las funciones anteriores,
la función apply se utiliza para realizar
operaciones en un objeto y devolver el objeto modificado.
- Also: La función also es similar a apply, pero
se utiliza con un objeto como argumento en lugar de
un receptor.*/

data class PerfilUsuario(var nombre: String?, var email: String?, var edad: Int, var activo: Boolean)

fun main() {
    // 1. apply: Ideal para configurar las propiedades de un objeto recién creado y devolver el objeto
    val perfil = PerfilUsuario(null, null, 0, false).apply {
        nombre = "Carlos Pérez"
        email = "carlos@example.com"
        edad = 30
        activo = true
    }

    // 2. also: Ideal para efectos secundarios (como hacer un log) y devolver el mismo objeto intacto
    val perfilConfigurado = perfil.also {
        println("[also] Perfil inicializado para: ${it.nombre}")
    }

    // 3. let: Ideal para comprobaciones de nulabilidad y operar con el objeto usando 'it'
    perfilConfigurado.email?.let { correo ->
        println("[let] Enviando correo de confirmación a: $correo")
    }

    // 4. run: Ideal para realizar operaciones usando 'this' y devolver el resultado de la última expresión
    val descripcion = perfilConfigurado.run {
        "[run] Resumen: $nombre, $edad años, Activo: $activo"
    }
    println(descripcion)

    // 5. with: Similar a run, pero recibe el objeto como argumento y se usa para agrupar llamadas
    with(perfilConfigurado) {
        println("[with] Verificación completada para el usuario: $nombre")
    }
}