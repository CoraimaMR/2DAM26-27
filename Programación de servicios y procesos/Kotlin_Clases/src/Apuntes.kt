
fun main() {

    // ------------------ CLASE 01 ------------------

//    +-------------------------+---------------------------------+------------------------------------------+
//    | Combinación             | ¿Puedes añadir/quitar ítems?    | ¿Puedes cambiar la lista completa (=)?    |
//    +-------------------------+---------------------------------+------------------------------------------+
//    | val + listOf            | NO                              | NO (Máxima seguridad)                    |
//    | var + listOf            | NO                              | SÍ (Sustituyes por otra lista)           |
//    | val + mutableListOf     | SÍ                              | NO (El contenedor es fijo)               |
//    | var + mutableListOf     | SÍ                              | SÍ (Flexibilidad total)                  |
//    +-------------------------+---------------------------------+------------------------------------------+

    // 🔴 val + listOf: Totalmente congelada
    val listaFija = listOf("Ana", "Pedro")
    // listaFija.add("Luis") ❌ ERROR (No tiene el método .add)
    // listaFija = listOf("Juan") ❌ ERROR (val no se puede reasignar)

    // 🟢 val + mutableListOf: El contenido cambia, la variable no
    val listaCreciente = mutableListOf("Ana", "Pedro")
    listaCreciente.add("Luis") //  CORRECTO: Ahora tiene 3 elementos
    listaCreciente[0] = "María" //  CORRECTO: Puedes modificar un índice
    // listaCreciente = mutableListOf("Juan") ❌ ERROR (val no se puede reasignar)

    // 🔴 var + listOf: No modificas elementos, pero sí la lista entera
    var listaIntercambiable = listOf("Ana", "Pedro")
    // listaIntercambiable.add("Luis") ❌ ERROR
    listaIntercambiable = listOf("Juan", "Carlos") //  CORRECTO (Borraste la anterior, pusiste esta)

    // 🟢 var + mutableListOf: Libertad total (Poco recomendado a menos que sea necesario)
    var listaLibre = mutableListOf("Ana", "Pedro")
    listaLibre.add("Luis") //  CORRECTO
    listaLibre = mutableListOf("Juan") //  CORRECTO

}