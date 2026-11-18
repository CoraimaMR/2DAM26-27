import 'package:petshop/models/mascota.dart';

class VentaService {
  double calcularDescuento(double precio, {double porcentaje = 0.1}) {
    return precio - (precio * porcentaje);
  }

  double calcularPrecioConIVA(double precio) {
    double iva = 0.21;
    return precio * (1 + iva);
  }
}

// ------------------- CLASE 03 - FUNCIONES DE CÁLCULO -------------------

// Funciones de cálculo
double calcularIVA(double precio) {
  return precio * 0.21; // IVA al 21%
}

double calcularPrecioFinal(double precio, double descuento) {
    return precio - descuento;
}

double calcularVenta({required double precio, required double descuento}) {
  return precio - descuento;
}

double calcularVenta2(double precio, double descuento) {
  return precio - descuento;
}

double calcularPrecioConIVA(double precio) {
  double iva = calcularIVA(precio);
  return precio + iva;
}

double aplicarDescuento(double precio, double descuento) => precio - descuento;

double calcularVenta3({required double precio, required double? descuento}) {
  // Si el descuento es nulo, se considera como 0
  return precio - (descuento ?? 0);
}

double procesarPrecio(double precio, double Function(double) operacion) {
  return operacion(precio);
}

var productoPrecioConIVA = procesarPrecio(200, (precio) => precio * 1.21); // Usando una función anónima (lambda) para calcular el precio con IVA

var productoIVA = procesarPrecio(200, (precio) => precio * 0.21); // Usando una función anónima (lambda) para calcular el IVA

var productoOferta10 = procesarPrecio(200, (precio) => precio - 10); // Usando una función anónima (lambda) para aplicar un descuento de 10

var productoDescuento10 = procesarPrecio(200, (precio) => precio - 10); // Usando una función anónima (lambda) para aplicar un descuento de 10

// // Función para buscar mascotas por especie
// List<Mascota> buscarPorEspecieMal(List<Mascota> mascotas, String especie) {
//   return mascotas.where((mascota) => mascota.especie == especie).toList();
// } /* mal - trae todas las mascotas, no solo las de la especie especificada ademas de que 
// no es eficiente, ya que recorre toda la lista de mascotas y luego filtra las que coinciden 
// con la especie y podria ser demasiados para una interfaz. */

// var nombres = mascotas.map((mascota) => mascota.nombre).toList(); // Usando map para obtener una lista de nombres de mascotas
