package `Tema0 POO`

fun main(){

    //Polimorfismo
    val equipo: List<Pokemon> = listOf(
        Pikachu("Poo.Pikachu", 25, Tipo.ELECTRICO),
        Charmander("Poo.Charmander", 4, Tipo.FUEGO)
    )

    for (pokemon in equipo){
        pokemon.atacar()
        pokemon.recibirAtaque(25)
        println()
    }


    // Interfaces

    val pikachita = Pikachu("Pikapi", 25, Tipo.ELECTRICO)

    pikachita.ataqueEspecial()
    pikachita.esquivar()

    val entrenador = object {
        val nombre = "Ash"
        val pokemonAsh= equipo;
        fun llamarPokemon(){
            for(pokemon in pokemonAsh){
                println(pokemon.toString()+" tu turno es ahora!")
            }
        }
    }

    entrenador.llamarPokemon()


    //val Charizard = Poo.Pokemon("Charizard", 6, Poo.Tipo.FUEGO, Poo.Tipo.VOLADOR))
}