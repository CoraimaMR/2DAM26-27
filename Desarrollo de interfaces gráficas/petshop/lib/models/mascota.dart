
class Mascota {

  int id;
  String nombre;
  String especie;
  int edad;
  double precio;
  bool disponible;

  Mascota(
    this.id,
    this.nombre,
    this.especie,
    this.edad,
    this.precio,
    this.disponible
  );

    void mostrarInformacion(){
      print(
        '$nombre ($especie) - $edad años - $precio €'
      );
    }
    
    @override
    String toString(){
      return '$nombre ($especie)';
    }


}