import 'animal.dart';

class Pez extends Animal {

  Pez(String nombre, int edad) : super(nombre, edad);

  @override
  String emitirSonido() => "¡Glup glup!";

}