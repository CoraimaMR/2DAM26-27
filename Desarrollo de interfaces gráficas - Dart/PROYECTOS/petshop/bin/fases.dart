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

  final toby = Mascota(1, 'Toby', 'Perro', 2, 500, true);
  final luna = Mascota(2, 'Luna', 'Gato', 1, 300, true);
  final piolin = Mascota(3, 'Piolín', 'Ave', 1, 120, true);
  final nemo = Mascota(4, 'Nemo', 'Pez', 1, 25, true);

  for (final mascota in [toby, luna, piolin, nemo]) {
    mascota.mostrarInformacion();
  }

  final cliente = Cliente(1, 'Ana López', 'ana@email.com');
  final pedido = Pedido(1, cliente, toby, DateTime(2026, 11, 6));
  pedido.mostrarResumen();

  print('\n=========== FASE 02: FUNCIONES ===========');

  final catalogoFunciones = [
    Mascota(1, 'Toby', 'Perro', 2, 500, true),
    Mascota(2, 'Luna', 'Gato', 1, 300, true),
    Mascota(3, 'Nemo', 'Pez', 1, 25, true),
    Mascota(4, 'Piolín', 'Ave', 1, 120, true),
  ];

  final ventaService = VentaService();

  print('Compras < 50€: ${ventaService.calcularEnvio(35)}'); // 5,00 €
  print('Compras >= 50€: ${ventaService.calcularEnvio(50)}'); // Gratis

  for (final mascota in catalogoFunciones) {
    print('\nNombre: ${mascota.nombre}');
    print('Precio original: ${mascota.precio} €');
    print('Precio con 10% descuento: ${ventaService.calcularDescuento(mascota.precio)} €');
    print('Precio con IVA: ${ventaService.calcularPrecioConIVA(mascota.precio)} €');
  }

  toby.generarTicket();

  print('\n=========== FASE 03: HERENCIA ===========');

  final List<Animal> inventario = [
    Perro('Toby', 2),
    Gato('Luna', 1),
    Ave('Piolín', 1),
    Pez('Nemo', 1),
    Conejo('Bugs', 2),
  ];

  print(inventario[4].emitirSonido());

  for (final animal in inventario) {
    print('\nNombre: ${animal.nombre}');
    print('Edad: ${animal.edad} años');
    print('Sonido: ${animal.emitirSonido()}');
  }

  inventario[0].presentar();
  
  // print('\n=========== FASE 04: COLECCIONES ===========');

  // final List<Mascota> catalogo = [
  //   Mascota(1, 'Toby', 'Perro', 2, 500, true),
  //   Mascota(2, 'Luna', 'Gato', 1, 300, true),
  //   Mascota(3, 'Nemo', 'Pez', 1, 25, true),
  //   Mascota(4, 'Piolín', 'Ave', 1, 120, true),
  // ];

  // final Set<String> categorias =
  //     catalogo.map((mascota) => mascota.especie).toSet();
  // final Map<String, int> stock = {
  //   'Pienso': 50,
  //   'Correa': 15,
  //   'Jaula': 10,
  //   'Juguete': 30,
  // };

  // print('Categorías: $categorias');

  // print('\nStock inferior a 20 unidades:');
  // stock.forEach((producto, cantidad) {
  //   if (cantidad < 20) {
  //     print('$producto -> $cantidad');
  //   }
  // });

  // final total = catalogo.fold<double>(
  //   0,
  //   (acumulado, mascota) => acumulado + mascota.precio,
  // );
  // print('Valor total del catálogo: ${total.toStringAsFixed(2)} €');

  // catalogo.sort((a, b) => a.precio.compareTo(b.precio));
  // print('\nMascotas ordenadas por precio:');
  // for (final mascota in catalogo) {
  //   print('${mascota.nombre}: ${mascota.precio} €');
  // }

  // print('\n=========== FASE 05: NULL SAFETY ===========');

  // final clientes = [
  //   ClienteNulo(1, 'Ana', 'ana@email.com', '600123123', 'Madrid', null),
  //   ClienteNulo(2, 'Luis', 'luis@email.com', '611987654', null, null),
  //   ClienteNulo(3, 'Marta', 'marta@email.com', null, null, null),
  // ];

  // for (final clienteNulo in clientes) {
  //   clienteNulo.mostrarInformacion();
  // }

  // final mascotasNulas = [
  //   MascotaNula(1, 'Toby', 'Perro', null),
  //   MascotaNula(2, 'Luna', 'Gato', 'Vacunada'),
  // ];
  // for (final mascota in mascotasNulas) {
  //   print(
  //     '${mascota.nombre} - ${mascota.especie} - '
  //     '${mascota.observaciones ?? "Sin observaciones"}',
  //   );
  // }

  // print('\n=========== FASE 06: EXTENSIONS ===========');

}
