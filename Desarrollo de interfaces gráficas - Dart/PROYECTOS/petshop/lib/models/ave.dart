import 'animal.dart';

class Ave extends Animal {
  
  Ave(String nombre, int edad) : super(nombre, edad);

  @override
  String emitirSonido() => "¡Pío pío!";

}