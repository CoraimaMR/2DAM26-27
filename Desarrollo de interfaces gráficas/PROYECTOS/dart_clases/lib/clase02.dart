
void main () {

  nullAware();

  var eevee = Pokemon()
    ..nombre = 'Eevee'
    ..nivel = 5
    ..subirNivel()
    ..mostrarInfo();

  eevee.presentarEvolucion(eevee.nombre,);
  eevee.presentarEvolucion(eevee.nombre, 2);
  eevee.presentarEvolucion(eevee.nombre, 27);

  Pokemon().crearPokemon();
  Pokemon().crearPokemon(param1: 'Charmander', param2: 67);

  int cuadradoNivel(int n) => n * n;
  cuadradoNivel(eevee.nivel);
  
  var saludable = "Eevee";
  var saludo = Pokemon().saludar(saludable);
  saludo;

  var lista = <String>['Pikachu', 'Bulbasaur', 'Charmander']; // Mutable list
  List<String> lista2 = ['Pikachu', 'Bulbasaur', 'Charmander']; // Inmutable list
  var lista3 = <String>[]; // Empty list
    lista3.add('Pikachu');
    lista3.add('Bulbasaur');
    lista3.add('Charmander');
  lista.addAll(['Pidgey', 'Rattata', 'Zubat']);
  print(lista);
  print(lista2);
  print(lista3[0]);

}

void nullAware() {
  // Null Aware Operator ?
  String ? nombre = "Coraima";
  print(nombre?.length);

  // Null Aware Operator ??
  String ? pokemon = null;
  print(pokemon ?? 'No hay pokemon');

  // Null Aware Operator !
  String ? trainer = "Ash";
  print(trainer!.length);
}

class Pokemon {

  String nombre = '';
  int nivel = 0;

  void mostrarInfo() {
    print('Nombre: $nombre');
    print('Nivel: $nivel');
  }

  void subirNivel() {
    nivel++;
  }

  void presentarEvolucion(String nombre, [int nivel = 25]) {
    if (nivel >= 25) {
      print('$nombre ha evolucionado');
    } else {
      print('$nombre no ha evolucionado');
    }
  }

  void crearPokemon ({String param1 = "Ditto", int param2 = 0}) {
    print("Se ha creado un pokemon con nombre: $param1 y nivel: $param2");
  }

  Function saludar = (String nombre) {
    print("Hola, soy $nombre");
  };
}