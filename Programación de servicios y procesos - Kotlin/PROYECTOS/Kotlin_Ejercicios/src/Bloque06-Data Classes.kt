/*Actividad
Diseña un sistema de administración de productos utilizando data classes.
Implementa funcionalidades que permitan copiar objetos, desestructurarlos y compararlos entre sí.*/

fun main() {

    val productoOriginal = ProductoEjemplo("Teclado", 49.99, 10)
    val productoActualizado = productoOriginal.copy(precio = 44.99, stock = 8)

    val (nombre, precio, stock) = productoOriginal

    println("Producto original: $productoOriginal")
    println("Copia actualizada: $productoActualizado")
    println("Desestructurado: nombre=$nombre, precio=$precio €, stock=$stock")
    println("¿Son iguales? ${productoOriginal == productoActualizado}")
    println("¿La copia conserva los mismos datos? ${productoOriginal == productoOriginal.copy()}")
}

data class ProductoEjemplo(val nombre: String, val precio: Double, val stock: Int)