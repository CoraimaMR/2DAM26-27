import 'animal.dart';

class Perro extends Animal {
  
  Perro(String nombre, int edad) : super(nombre, edad);

  @override
  void emitirSonido() {
    print("¡Guau!");
  }

  @override
  void mostrarFicha() {
    print("Perro: $nombre - $edad años");
  }

}