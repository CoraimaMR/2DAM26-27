abstract class Animal {

  final String nombre;
  final int edad;

  Animal(this.nombre, this.edad);

  String emitirSonido();

  void mostrarFicha() {
    print("$nombre - $edad años");
  }

  void presentar() {
    print("\nHola, soy $nombre.");
    print("Soy un ${runtimeType.toString().toLowerCase()}.");
    print("Tengo $edad años.");
    print(emitirSonido());
  }
}