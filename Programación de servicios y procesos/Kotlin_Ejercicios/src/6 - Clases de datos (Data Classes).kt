
/*Actividad: Diseña un sistema de administración de productos utilizando
 data classes. Implementa funcionalidades que permitan copiar objetos,
 desestructurarlos y compararlos entre sí.*/

/*En Kotlin, puedes crear clases de datos utilizando la palabra clave data. Las clases
de datos son una forma concisa de definir clases que contienen datos y no tienen
lógica adicional.
data class Persona(val nombre: String, val edad: Int)
En el ejemplo anterior, se define una clase de datos Persona que tiene dos
propiedades nombre y edad. La clase de datos se crea utilizando la palabra clave
data seguida del nombre de la clase y las propiedades de la clase.

Las clases de datos en Kotlin tienen las siguientes propiedades:
- Propiedades de solo lectura: Las propiedades de una clase de datos son de solo lectura y
no se pueden modificar una vez que se han inicializado.
- Métodos toString(), equals() y hashCode(): Las clases de datos en Kotlin generan
automáticamente los métodos toString(), equals() y hashCode() a partir de las
propiedades de la clase.
- Métodos componentN(): Las clases de datos en Kotlin generan automáticamente métodos
componentN() que te permiten desestructurar objetos de la clase en variables individuales.
- Método copy(): Las clases de datos en Kotlin generan automáticamente un método copy()
que te permite crear copias de objetos de la clase con propiedades modificadas.*/

// 1. Definición de la clase de datos (data class)
data class Producto(val id: Int, val nombre: String, val precio: Double, val stock: Int)

fun main() {
    println("=== SISTEMA DE ADMINISTRACIÓN DE PRODUCTOS ===\n")

    // Creación de productos
    val producto1 = Producto(1, "Laptop", 800.0, 10)
    val producto2 = Producto(1, "Laptop", 800.0, 10) // Mismos datos que el producto 1
    val producto3 = Producto(2, "Mouse", 25.0, 50)

    // 2. Método toString() automático y Comparación (equals())
    println("Producto 1: $producto1") // Usa toString() automáticamente
    println("¿Producto 1 es igual a Producto 2?: ${producto1 == producto2}") // Compara valores, no referencias
    println("¿Producto 1 es igual a Producto 3?: ${producto1 == producto3}\n")

    // 3. Método copy() (Crear una copia modificando solo el precio y el stock)
    val productoEnOferta = producto1.copy(precio = 720.0, stock = 15)
    println("Producto original: $producto1")
    println("Producto copiado y modificado: $productoEnOferta\n")

    // 4. Desestructuración (componentN())
    // Extrae las propiedades del objeto directamente en variables individuales
    val (id, nombre, precio, stock) = producto1
    println("Desestructuración del Producto 1:")
    println("- ID: $id")
    println("- Nombre: $nombre")
    println("- Precio: $precio")
    println("- Stock: $stock")
}