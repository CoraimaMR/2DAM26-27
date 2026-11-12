// import 'package:petshop/database/supabase_client.dart';
import 'package:petshop/models/mascota.dart';
import 'package:petshop/models/cliente.dart';
import 'package:petshop/models/pedido.dart';

void main() async {
 
  //Comprobación funcionamiento BBDD
  // var supabase = DatabaseConfig.client;
  // final mascotas = await supabase.from('mascotas').select();
  // print(mascotas);

  // Creación de instancias de las clases
  final mascota1 = Mascota(1, "Silver", "Perro", 3, 750, true);  
  final cliente1 = Cliente(1, "Coraima", "coraima@example.com");  
  final pedido1 = Pedido(1, cliente1, mascota1, DateTime.now());

 // Prints
 print("\n=== INFORMACIÓN CON PRINT ===");
 print(mascota1);
 print(cliente1);
 print(pedido1);

 // Metodos
 print("\n=== INFORMACIÓN CON MÉTODOS ===");
  mascota1.mostrarInformacion();
  cliente1.mostrarInformacion();
  pedido1.mostrarResumen();
}
