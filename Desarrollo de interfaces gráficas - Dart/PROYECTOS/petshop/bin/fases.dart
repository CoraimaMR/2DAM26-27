import 'dart:ffi';
import 'dart:io';

import 'package:petshop/database/supabase_client.dart';
import 'package:petshop/models/animal.dart';
import 'package:petshop/models/ave.dart';
import 'package:petshop/models/cliente.dart';
import 'package:petshop/models/conejo.dart';
import 'package:petshop/models/gato.dart';
import 'package:petshop/models/mascota.dart';
import 'package:petshop/models/pedido.dart';
import 'package:petshop/models/perro.dart';
import 'package:petshop/models/pez.dart';
import 'package:petshop/services/venta_service.dart';

void main() async {
  print('\n=========== FASE 00: SUPABASE ===========');

  try {
    var supabase = DatabaseConfig.client;
    final mascotas = await supabase.from('mascotas').select();
    // print('Filas recibidas: ${mascotas.length} -> $mascotas');

    print('=== MASCOTAS DISPONIBLES ===');
    for (final mascota in mascotas) {
      print('${mascota['nombre']} - ${mascota['especie']}');
    }
  } catch (error) {
    print('Error al consultar Supabase: $error');
  }

  print('\n=========== FASE 01: POO ===========');

  final listaMascotas = [
    Mascota(1, 'Toby', 'Perro', 2, 500, true, null, null),
    Mascota(2, 'Luna', 'Gato', 1, 300, true, null, null),
    Mascota(3, 'Nemo', 'Pez', 1, 25, true, null, null),
    Mascota(4, 'Piolín', 'Ave', 1, 120, true, null, null),
  ];

  for (final mascota in listaMascotas) {
    mascota.mostrarInformacion();
  }

  final cliente = Cliente(1, 'Ana López', 'ana@email.com', null, null);
  final pedido = Pedido(1, cliente, listaMascotas[0], DateTime(2026, 11, 6));
  pedido.mostrarResumen();

  print('\n=========== FASE 02: FUNCIONES ===========');

  final ventaService = VentaService();

  print('Compras < 50€: ${ventaService.calcularEnvio(35)}'); // 5,00 €
  print('Compras >= 50€: ${ventaService.calcularEnvio(50)}'); // Gratis

  for (final mascota in listaMascotas) {
    print('\nNombre: ${mascota.nombre}');
    print('Precio original: ${mascota.precio} €');
    print(
      'Precio con 10% descuento: ${ventaService.calcularDescuento(mascota.precio)} €',
    );
    print(
      'Precio con IVA: ${ventaService.calcularPrecioConIVA(mascota.precio)} €',
    );
  }

  listaMascotas[0].generarTicket();

  print('\n=========== FASE 03: HERENCIA ===========');

  final List<Animal> listaAnimales = [
    Perro('Toby', 2),
    Gato('Luna', 1),
    Ave('Piolín', 1),
    Pez('Nemo', 1),
    Conejo('Bugs', 2),
  ];

  stdout.write('Sonido del conejo: ');
  listaAnimales[4].emitirSonido();

  for (final animal in listaAnimales) {
    print('\nNombre: ${animal.nombre}');
    print('Edad: ${animal.edad} años');
    stdout.write('Sonido: ');
    animal.emitirSonido();
  }

  listaAnimales[0].presentar();

  print('\n=========== FASE 04: COLECCIONES ===========');

  final List<Mascota> inventario = [
    Mascota(1, 'Toby', 'Perro', 2, 500, true, null, null),
    Mascota(2, 'Luna', 'Gato', 1, 300, true, null, null),
    Mascota(3, 'Nemo', 'Pez', 1, 25, true, null, null),
    Mascota(4, 'Piolín', 'Ave', 1, 120, true, null, null),
  ];

  for (final mascota in inventario) {
    mascota.mostrarInformacion();
  }

  final Set<String> categorias = inventario
      .map((mascota) => mascota.especie)
      .toSet();
  final Map<String, int> stock = {
    'Pienso': 50,
    'Correa': 15,
    'Jaula': 10,
    'Juguete': 30,
  };

  print('\nCategorías: $categorias');
  print('\nStock inferior a 20 unidades:');
  stock.forEach((producto, cantidad) {
    if (cantidad < 20) {
      print('$producto -> $cantidad');
    }
  });

  final total = inventario.fold<double>(
    0,
    (acumulado, mascota) => acumulado + mascota.precio,
  );
  print('\nValor total del inventario: ${total.toStringAsFixed(2)} €');

  inventario.sort((a, b) => a.precio.compareTo(b.precio));
  print('\nMascotas ordenadas por precio:');
  for (final mascota in inventario) {
    print('${mascota.nombre}: ${mascota.precio} €');
  }

  print('\n=========== FASE 05: NULL SAFETY ===========');

  final clientes = [
    Cliente(1, 'Ana', 'ana@email.com', '600123123', 'Madrid'),
    Cliente(2, 'Luis', 'luis@email.com', '611987654', null),
    Cliente(3, 'Marta', 'marta@email.com', null, null),
  ];

  for (final cliente in clientes) {
    cliente.mostrarInformacion();
  }

  final mascotasNulas = [
    Mascota(1, 'Toby', 'Perro', 2, 500, true, null, null),
    Mascota(2, 'Luna', 'Gato', 1, 300, true, null, null),
  ];

  print('');
  for (final mascota in mascotasNulas) {
    mascota.mostrarInformacion();
  }

  // ???

  // print('\n=========== FASE 06: EXTENSIONS ===========');
}
