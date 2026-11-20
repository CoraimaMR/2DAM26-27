/*Actividad
Diseña una aplicación completa para la gestión de tareas que reúna todos los conceptos trabajados
durante el aprendizaje: clases, data classes, sealed classes, manejo seguro de nulos,
colecciones y scope functions.*/

fun main() {

    val tareas = mutableListOf(
        TareaEjemplo(1, "Preparar el proyecto", "Nora", EstadoTarea.Pendiente),
        TareaEjemplo(2, "Revisar los requisitos", null, EstadoTarea.EnProgreso)
    )

    val nuevaTarea = TareaEjemplo(
        3,
        "Entregar el proyecto",
        null,
        EstadoTarea.Pendiente
    ).apply {
        responsable = "Nora"
    }.also {
        println("Tarea creada: ${it.titulo}")
    }
    tareas.add(nuevaTarea)

    tareas.first { it.id == 1 }.estado = EstadoTarea.Completada
    val indiceReasignada = tareas.indexOfFirst { it.id == 2 }
    val tareaReasignada = tareas[indiceReasignada].copy(responsable = "Leo")
    tareas[indiceReasignada] = tareaReasignada

    println("Tarea copiada y reasignada: $tareaReasignada")
    println("Resumen de tareas:")
    tareas.forEach { tarea ->
        val responsable = tarea.responsable?.let { it } ?: "Sin asignar"
        println("- ${tarea.titulo}: ${tarea.estado.descripcion}, responsable: $responsable")
    }

    val pendientes = tareas.count { it.estado is EstadoTarea.Pendiente }
    println("Tareas pendientes: $pendientes")
}

data class TareaEjemplo(
    val id: Int,
    val titulo: String,
    var responsable: String?,
    var estado: EstadoTarea
)

sealed class EstadoTarea(val descripcion: String) {
    object Pendiente : EstadoTarea("Pendiente")
    object EnProgreso : EstadoTarea("En progreso")
    object Completada : EstadoTarea("Completada")
}