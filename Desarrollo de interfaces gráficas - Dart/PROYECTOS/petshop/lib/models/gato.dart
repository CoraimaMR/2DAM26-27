import 'animal.dart';

class Gato extends Animal {

  Gato(String nombre, int edad) : super(nombre, edad);

  @override
  String emitirSonido() => "¡Miau!";
  
  }