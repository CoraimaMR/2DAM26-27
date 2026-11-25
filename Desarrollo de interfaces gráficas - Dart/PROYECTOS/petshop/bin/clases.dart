import 'package:petshop/database/supabase_client.dart';
import 'package:petshop/models/animal.dart';
import 'package:petshop/models/ave.dart';
import 'package:petshop/models/gato.dart';
import 'package:petshop/models/mascota.dart';
import 'package:petshop/models/cliente.dart';
import 'package:petshop/models/pedido.dart';
import 'package:petshop/models/perro.dart';
import 'package:petshop/models/pez.dart';
import 'package:petshop/services/venta_service.dart';

void main() async {
 
  // // ----------- CLASE 01 -----------
  // //Comprobación funcionamiento BBDD
  // var supabase = DatabaseConfig.client;
  // final mascotas = await supabase.from('mascotas').select();
  // print(mascotas);

  // // ----------- CLASE 02 -----------
  // // Creación de instancias de las clases
  // final mascota1 = Mascota(1, "Silver", "Perro", 3, 750, true);  
  // final cliente1 = Cliente(1, "Coraima", "coraima@example.com");  
  // final pedido1 = Pedido(1, cliente1, mascota1, DateTime.now());

  // // Prints
  // print("\n=== INFORMACIÓN CON PRINT ===");
  // print(mascota1);
  // print(cliente1);
  // print(pedido1);

  // // Metodos
  // print("\n=== INFORMACIÓN CON MÉTODOS ===");
  // mascota1.mostrarInformacion();
  // cliente1.mostrarInformacion();
  // pedido1.mostrarResumen();

  // // ----------- CLASE 03 -----------
  // // Creación de funciones de cálculo
  // final ventaService = VentaService();
  
  // var calculo1 = ventaService.calcularDescuento(100);
  // var calculo2 = ventaService.calcularDescuento(100, porcentaje: 0.2);
 
  // print("\n=== RESULTADOS ===");
  // print("Precio de 100 con descuento de 10: $calculo1 €");
  // print("Precio de 100 con descuento de 20: $calculo2 €");

  // // ----------- CLASE 04 -----------
  // // Creación de instancias de las extensiones de la clase Animal
  // final coco = Perro("Coco", 3);
  // print("\n=== DATOS DE LA INSTANCIA COCO ===");
  // coco.mostrarFicha();
  // coco.emitirSonido();

  // // Listado de animales
  // List<Animal> animales = [
  //   Perro("Toby", 2),
  //   Pez("Nemo", 1),
  //   Gato("Nieves", 3),
  //   Ave("Piolin", 5)
  // ];

  // for (final animal in animales) {
  //   animal.mostrarFicha();
  //   animal.emitirSonido();
  // }

  // if (animales[2] is Gato) {
  //   print("Es un gato");
  // } else if (animales[2] is Perro) {
  //   print("Es un perro");
  // } else if (animales[2] is Animal) {
  //  print("Es Ave o Pez");
  // }

  // // Listados de mascotas
  // List<Mascota> catalogo = [
  //   Mascota(1, 'Toby', 'Perro', 2, 500, true),
  //   Mascota(2, 'Luna', 'Gato', 1, 300, true),
  //   Mascota(3, 'Nemo', 'Pez', 1, 25, true),
  // ]; 

  // // Busqueda segura
  // try {
  //   var perrito1 = catalogo.firstWhere((m) => m.nombre == "Toby");
  // } catch (e) {
  //   print("Error: " + e.toString());
  // }

  // // Filtrado (where)
  // var perros = catalogo.where((m) => m.nombre == "Toby").toList();

  // // Transformación de datos (map)
  // var nombres = catalogo.map((m) => m.nombre).toList();
  // var nombres2 = perros.map((m) => m.edad).toList();
  
  // // Ordenamiento (sort)
  // catalogo.sort((a,b) => a.edad.compareTo(b.edad));
  // print(catalogo);

  // // Manejo de Conjuntos (Set<String>)
  // Set<String>nombresCatalogo = {
  //   'Toby', 'Nemo', 'Piolin' 
  // };

  // nombresCatalogo.add('Coco');
  // Set<String> conj2={'Tom', 'Billy'};
  // nombresCatalogo.addAll(conj2);
  // nombresCatalogo.contains('Coco');

}
