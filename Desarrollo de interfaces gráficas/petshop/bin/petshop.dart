

import 'package:petshop/database/supabase_client.dart';
import 'package:petshop/models/mascota.dart';


void main() async {
  final mascota1 = Mascota(4, 'Tod', 'zorro', 1, 1000.0, true);
  mascota1.mostrarInformacion();
  print(mascota1);
  
  //Comprobación funcionamiento BBDD
  var supabase = DatabaseConfig.client;
  final mascotas = await supabase.from('mascotas').select();
  print(mascotas);

 
}
