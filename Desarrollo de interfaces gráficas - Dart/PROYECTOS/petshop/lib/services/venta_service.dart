
class VentaService {
  double calcularDescuento(double precio, {double porcentaje = 0.1}) {
    return precio - (precio * porcentaje);
  }

  double calcularPrecioConIVA(double precio) {
    double iva = 0.21;
    return precio * (1 + iva);
  }

  String calcularEnvio(double compra) => compra < 50 ? "5 €" : "Gratis";
}