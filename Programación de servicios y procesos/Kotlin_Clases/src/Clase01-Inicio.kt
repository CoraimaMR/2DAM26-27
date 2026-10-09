//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val name = "Kotlin"
    val nombre: String = "Dart"

    val cadenita: String = 23.toString()

    val numerito: Int? = "23sadf".toIntOrNull();

    var abdc = "gjhsdjf"

    abdc = abdc + "23"

    print(abdc)

    val lista = mutableListOf(1, 2, 3, 4)
    lista[0] = 45
    lista.add(56)

    // No podemos alteramos la posición de memoria lista = lista.sort()


    var cambiante = mutableListOf(1, 45, 9, 67)
    cambiante.add(56)
    cambiante.sort()

}