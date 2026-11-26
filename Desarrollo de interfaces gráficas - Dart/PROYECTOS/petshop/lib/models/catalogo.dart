class Catalogo<T> {
  List<T>elementos = [];

  void agregar(T elemento) {
    elementos.add(elemento);
  }
}