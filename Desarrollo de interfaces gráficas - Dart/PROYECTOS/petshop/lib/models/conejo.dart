import 'animal.dart';

class Conejo extends Animal {
  
  Conejo(String nombre, int edad) : super(nombre, edad);

  @override
  void emitirSonido() {
    print("¡Sniff Sniff!");
  }

}