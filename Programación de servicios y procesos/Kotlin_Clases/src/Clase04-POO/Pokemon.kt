
abstract class Pokemon (
    protected val nombre: String,
    private val numeroPokedex: Int
    ){

    protected var vida = 100

    abstract fun atacar()

    open fun recibirAtaque(cantidad: Int){
        vida -= cantidad
        print("$nombre recibe $cantidad de daño. Le queda $vida puntos de vida.")
    }

    fun mostrarPokedex(){
        println("Pokedex n° $numeroPokedex")
    }

    override fun toString(): String {
        return nombre
    }

}