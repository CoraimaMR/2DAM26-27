// import 'package:petshop/database/supabase_client.dart';
import 'package:petshop/models/mascota.dart';
import 'package:petshop/models/cliente.dart';
import 'package:petshop/models/pedido.dart';
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
  // var calculo1 = calcularIVA(100);
  // var calculo2 = calcularPrecioFinal(100, 20);
  // var calculo3 = calcularVenta(precio: 100, descuento: 20);
  // var calculo4 = calcularPrecioConIVA(calcularVenta(precio: 100, descuento: 20));
  // var calculo5 = aplicarDescuento(100, 20);
  // var calculo6 = calcularVenta3(precio: 100, descuento: null);

  // print("\n=== RESULTADOS ===");
  // print("IVA de 100: $calculo1 €");
  // print("Precio final de 100 con descuento de 20: $calculo2 €");
  // print("Precio final de 100 con descuento de 20 (usando named parameters): $calculo3 €");
  // print("Precio final de 100 con descuento de 20 y con IVA: $calculo4 €");
  // print("Precio final de 100 con descuento de 20 (usando arrow function): $calculo5 €");
  // print("Precio final de 100 con descuento nulo:"); $calculo6 €

  // ----------- CLASE 04 -----------
}
