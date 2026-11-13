
class Cliente {

  // Atributos privados
  int _id;
  String _nombre;
  String _email;

  // Constructor
  Cliente(
    this._id,
    this._nombre,
    this._email
  );

  // Getters
  int get id => _id;
  String get nombre => _nombre;
  String get email => _email;

  // Setters
  set id(int valor) {if (valor > 0) _id = valor;}
  set nombre(String valor) {if (valor.isNotEmpty) _nombre = valor;}
  set email(String valor) {if (valor.isNotEmpty) _email = valor;}

  // Métodos
  void mostrarInformacion() {
    print(
      'Cliente ID: ${_id} - Nombre: ${_nombre} - Email: ${_email}'
    );
  }

  @override
  String toString() {
    return '$_nombre ($_email)';
  }
}