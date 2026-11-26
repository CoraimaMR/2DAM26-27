import 'package:petshop/models/mascota.dart';

extension PrecioExtension on double {
  String toEuro() {
    return '${toStringAsFixed(2)} €';
  }

  double aplicarDescuento(double porcentaje) {
    return this - (this * porcentaje/100);
  }
}

extension NombreExtension on String {
    String capitalizar() {
      return isEmpty ? this : this[0].toUpperCase() +substring(1);
    }
  }

extension FechaExtension on DateTime {
  String formatoSimple() {
    return '$day/$month/$year';
  }
}

extension MascotaExtension on Mascota {
  bool esCara() {
    return precio > 500;
  }

  bool esEconomica() {
    return precio <= 500;
  }

  String resumen() {
    return '$nombre - $edad - ${precio.toEuro()}';
  }
}