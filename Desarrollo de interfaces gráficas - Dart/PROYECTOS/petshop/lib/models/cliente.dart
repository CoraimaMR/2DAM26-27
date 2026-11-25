
class Cliente {

  // Atributos privados
  int id;
  String nombre;
  String email;
  String? telefono;
  String? direccion;

  // Constructor
  Cliente(
    this.id,
    this.nombre,
    this.email,
    this.telefono,
    this.direccion
  );

  // Getters
  int get getId => id;
  String get getNombre => nombre;
  String get getEmail => email;
  String? get getTelefono => telefono;
  String? get getDireccion => direccion;

  // Setters
  set setId(int valor) {if (valor > 0) id = valor;}
  set setNombre(String valor) {if (valor.isNotEmpty) nombre = valor;}
  set setEmail(String valor) {if (valor.isNotEmpty) email = valor;}
  set setTelefono(String valor) {if (valor.isNotEmpty) telefono = valor;}
  set setDireccion(String valor) {if (valor.isNotEmpty) direccion = valor;}

  // Métodos
  void mostrarInformacion() {
    print(
      'Cliente ID: $id - Nombre: $nombre - Email: $email'
    );
  }

  @override
  String toString() {
    return '$nombre ($email)';
  }
}