import 'package:petshop/database/supabase_client.dart';
import 'package:petshop/extensions/precio_extension.dart';
import 'package:petshop/models/animal.dart';
import 'package:petshop/models/ave.dart';
import 'package:petshop/models/caja.dart';
import 'package:petshop/models/gato.dart';
import 'package:petshop/models/mascota.dart';
import 'package:petshop/models/cliente.dart';
import 'package:petshop/models/pedido.dart';
import 'package:petshop/models/perro.dart';
import 'package:petshop/models/pez.dart';
import 'package:petshop/repositories/repository.dart';
import 'package:petshop/services/venta_service.dart';

void main() async {
 
  print('\n=========== CLASE 01 ===========');
  //Comprobación funcionamiento BBDD
  var supabase = DatabaseConfig.client;
  final mascotas = await supabase.from('mascotas').select();
  print(mascotas);

  print('\n=========== CLASE 02 ===========');
  // Creación de instancias de las clases
  final mascota1 = Mascota(1, 'Silver', 'Perro', 3, 750, true, null, null);  
  final cliente1 = Cliente(1, 'Coraima', 'coraima@example.com', null, null);  
  final pedido1 = Pedido(1, cliente1, mascota1, DateTime.now());

  // Prints
  print('=== INFORMACIÓN CON PRINT ===');
  print(mascota1);
  print(cliente1);
  print(pedido1);

  // Metodos
  print('\n=== INFORMACIÓN CON MÉTODOS ===');
  mascota1.mostrarInformacion();
  cliente1.mostrarInformacion();
  pedido1.mostrarResumen();

  print('\n=========== CLASE 03 ===========');
  // Creación de funciones de cálculo
  final ventaService = VentaService();
  
  var calculo1 = ventaService.calcularDescuento(100);
  var calculo2 = ventaService.calcularDescuento(100, porcentaje: 0.2);
 
  print('=== RESULTADOS ===');
  print('Precio de 100 con descuento de 10: $calculo1 €');
  print('Precio de 100 con descuento de 20: $calculo2 €');

  print('\n=========== CLASE 04 ===========');
  // Creación de instancias de las extensiones de la clase Animal
  final coco = Perro('Coco', 3);
  print('=== DATOS DE LA INSTANCIA COCO ===');
  coco.mostrarFicha();
  coco.emitirSonido();
  print('');

  // Listado de animales
  List<Animal> animales = [
    Perro('Toby', 2),
    Pez('Nemo', 1),
    Gato('Nieves', 3),
    Ave('Piolin', 5)
  ];

  for (final animal in animales) {
    animal.mostrarFicha();
    animal.emitirSonido();
  }

  print('\n¿Que tipo de animal es?');
  if (animales[2] is Gato) {
    print('Es un gato');
  } else if (animales[2] is Perro) {
    print('Es un perro');
  } else if (animales[2] is Animal) {
   print('Es Ave o Pez');
  }

  // Listados de mascotas
  List<Mascota> catalogo = [
    Mascota(1, 'Toby', 'Perro', 2, 500, true, null, null),
    Mascota(2, 'Luna', 'Gato', 1, 300, true, null, null),
    Mascota(3, 'Nemo', 'Pez', 1, 25, true, null, null),
  ]; 

  // Busqueda segura
  try {
    var perrito1 = catalogo.firstWhere((m) => m.nombre == 'Toby');
  } catch (e) {
    print('Error: ' + e.toString());
  }

  // Filtrado (where)
  var perros = catalogo.where((m) => m.nombre == 'Toby').toList();

  // Transformación de datos (map)
  var nombres = catalogo.map((m) => m.nombre).toList();
  var nombres2 = perros.map((m) => m.edad).toList();
  
  // Ordenamiento (sort)
  catalogo.sort((a,b) => a.edad.compareTo(b.edad));
  print('\n$catalogo');

  // Manejo de Conjuntos (Set<String>)
  Set<String>nombresCatalogo = {
    'Toby', 'Nemo', 'Piolin' 
  };

  nombresCatalogo.add('Coco');
  Set<String> conj2={'Tom', 'Billy'};
  nombresCatalogo.addAll(conj2);
  nombresCatalogo.contains('Coco');

  print('\n=========== CLASE 05 ===========');
  Map<String, int> stock = {
    'Pienso': 50,
    'Juguete': 30,
    'Correa': 15
  };

  stock['Penso'] = 40;
  stock.containsKey('Juguete');
  
  print('Map stock:');
  print(stock.toString());
  print(stock.keys.toString());
  print(stock.values.toString());

  catalogo.any((m) => m.precio > 200);
  catalogo.every((m) => m.disponible);

  print('\nFold: ');
  var total = catalogo.fold<double>(0, (acum, mascota1) => acum + mascota1.precio);
  print(total);

  // NULL SAFETY
  print('\nNull safety;');
  String? nombre = 'Rafa?';
  nombre = 'Monica';
  nombre = null;
  print(nombre);
  
  var cliente = Cliente(1, 'Fermin', 'fermin@gmail.com', '+34000000000', null);
  print(cliente.direccion?.length);

  print('\nDireccion de cliente: ${cliente.direccion ?? 'Sin direciion'}');
  print('Telefono de cliente: ${cliente.telefono ?? 'Sin telefono'}');

  print('\n=========== CLASE 06 ===========');
  // Metodos en precio_extension.dart
  print('Convertir a .toEuro(): ${mascota1.precio.toEuro()}');
  print('Convertir a .capitalizar(): ${'olga m.'.capitalizar()}');
  print('Convertir a .capitalizar(): ${'olga m.'.split(' ').map((m) => m.capitalizar())}');
  print('Convertir a .formatoSimple(): ${DateTime.now().formatoSimple()}');
  print('Convertir a .esCara(): ${mascota1.esCara()}');
  print('Convertir a .resumen(): ${mascota1.resumen()}');
  print('Convertir a .aplicarDescuento(): ${mascota1.precio.aplicarDescuento(10)}');

  // Class caja.dart
  var caja1 = Caja<int>(1);
  var caja2 = Caja<String>('Fer');

  print('\nMostrar contenido caja 1: ${caja1.obtener()}');

  // Class repository.dart
  final mascotaRepository = Repository<Mascota>();
  mascotaRepository.add(mascota1);
  print('Mostrar contenido mascotaRepository: ${mascotaRepository.getAll().toString()}');
  
}
