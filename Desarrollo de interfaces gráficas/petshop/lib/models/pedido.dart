import 'cliente.dart';
import 'mascota.dart';

class Pedido {

  // Atributos privados
  int _id;
  Cliente _cliente;
  Mascota _mascota;
  DateTime _fecha;

  // Constructor
  Pedido(
    this._id,
    this._cliente,
    this._mascota,
    this._fecha
  );

  // Getters
  int get id => _id;
  Cliente get cliente => _cliente;
  Mascota get mascota => _mascota;
  DateTime get fecha => _fecha;
  
  // Setters
  set id(int valor) {if (valor > 0) _id = valor;}
  set cliente(Cliente valor) {_cliente = valor;}
  set mascota(Mascota valor) {_mascota = valor;}
  set fecha(DateTime valor) {_fecha = valor;}

  // Métodos
@override
  String toString() {
    String mascota = _mascota.toString();
    return '${_cliente.toString()} compra $_mascota';
  }

  void mostrarResumen() {
    print('''
===== PEDIDO $_id =====
Cliente: $_cliente
Mascota: $_mascota
Precio: ${_mascota.precio} €
Fecha: $_fecha''');
  }
}