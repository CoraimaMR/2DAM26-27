abstract class Animal {

  final String nombre;
  final int edad;

  Animal(this.nombre, this.edad);

  void emitirSonido();

  void mostrarFicha() {
    print("$nombre - $edad años");
  }

}