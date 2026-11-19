# Guía Completa UD1: Persistencia en Ficheros con Java (TXT, CSV, JSON y XML)

Este documento reúne la teoría esencial basada en la estructura real del proyecto (`VentanaPrincipal`, `TiendaService`, `GestorFicheros`, `Cliente`, `Producto`) y los ficheros de datos (`datos/`), junto con el cuestionario de 30 preguntas resuelto y comentado para repaso.

---

# PARTE I: APUNTES Y ESQUEMAS DEL PROYECTO

## 1. Arquitectura General del Proyecto de Ficheros

El proyecto organiza el acceso a ficheros en capas bien diferenciadas para separar la interfaz gráfica de la lógica de negocio y del formato de almacenamiento.

```text
+--------------------+      +-----------------+      +------------------+      +-------------------+
|  VentanaPrincipal  | ---> |  TiendaService  | ---> |  GestorFicheros  | ---> | Sistema Ficheros  |
|  (Interfaz Swing)  |      | (Lógica/Valid.) |      | (E/S y Parsing)  |      | (.txt/.csv/.json) |
+--------------------+      +-----------------+      +------------------+      +-------------------+
         |                          |                         |
         v                          v                         v
  JTable / Botones          Lista en memoria          Serialización / Parsing
  (Vista de datos)          (Validación previa)       (NIO.2, Jackson, CSV)
```

* **`VentanaPrincipal` (Vista):** Gestiona la interfaz gráfica (`JTable`, botones de importar/exportar). Antes de sustituir la lista visible en pantalla con datos importados, **valida la importación y sus resultados** para evitar que un fallo borre o corrompa los datos actuales con resultados incompletos.
* **`TiendaService` (Servicio):** Administra las colecciones en memoria (`List<Cliente>`, `List<Producto>`) y coordina las operaciones de importación y exportación.
* **`GestorFicheros` (Persistencia):** Implementa la lectura y escritura en los cuatro formatos soportados: **TXT, CSV, JSON y XML**.
* **`Cliente` / `Producto` (Modelo):** Clases POJO que encapsulan los atributos de cada entidad.

---

## 2. Manejo de Ficheros de Texto y Java NIO.2

Para leer y escribir ficheros de texto plano (`.txt` y `.csv`) de forma eficiente y segura se emplea la API **Java NIO.2** (`java.nio.file`) junto con flujos con búfer (`java.io`).

### Esquema del Flujo de Lectura y Escritura de Texto

```text
[Objeto Path] (Representa la ruta, NO abre el fichero)
      │
      ├──> Files.newBufferedReader(path, StandardCharsets.UTF_8)
      │         └──> BufferedReader.readLine() ──> Devuelve String (línea) o null (fin de fichero)
      │
      └──> Files.newBufferedWriter(path, StandardCharsets.UTF_8) [Por defecto: crea o trunca]
                └──> BufferedWriter.write(...) + BufferedWriter.newLine() (salto de línea del SO)
```

### Conceptos Fundamentales de E/S

* **`Path`:** Representa una ruta del sistema de ficheros (por ejemplo, `Path.of("datos/clientes.csv")`). Por sí mismo no abre ningún flujo ni archivo.
* **Codificación `UTF-8` (`StandardCharsets.UTF_8`):** Es el estándar recomendado para intercambiar texto, ya que soporta caracteres internacionales (tildes, `ñ`, símbolos) y evita depender de la codificación por defecto del sistema operativo.
* **`BufferedReader` y `readLine()`:** Permite leer un fichero de texto línea a línea. Cuando alcanza el final del fichero (EOF), devuelve **`null`** (mientras que una línea en blanco devuelve una cadena vacía `""`).
* **`BufferedWriter` y `newLine()`:** Escribe texto en un flujo de salida. El método `newLine()` inserta el separador de línea adecuado para el sistema operativo actual (`\n` en Linux/macOS, `\r\n` en Windows).
* **Truncado por defecto en `Files.newBufferedWriter`:** Si se llama sin la opción `StandardOpenOption.APPEND` sobre un fichero existente, **se trunca (borra) su contenido anterior** antes de empezar a escribir.
* **`try-with-resources`:** Garantiza que todos los recursos que implementan `AutoCloseable` (`BufferedReader`, `BufferedWriter`, etc.) se cierren automáticamente al salir del bloque, incluso si ocurre una excepción.
* **`IOException`:** Excepción comprobada (*checked exception*) típica ante cualquier fallo de entrada/salida (archivo no encontrado, falta de permisos, disco lleno, etc.).

---

## 3. Formatos Delimitados: TXT vs. CSV (`parseCsv`)

### A. Formato TXT Delimitado por Punto y Coma (`;`)
* Cada línea representa un registro con sus campos separados por `;` (ejemplo: `1;Ana Pérez;ana@email.com;true`).
* **Conversión de tipos:** Al leer desde texto, todos los campos llegan como `String`. Para reconstruir el ID numérico se usa **`Integer.parseInt(texto)`** (que convierte `String` en `int` y puede lanzar `NumberFormatException` si el texto no es un número válido).
* **Riesgo de `split(";")`:** Si el contenido de un campo incluye un punto y coma `;` literal, `split(";")` lo interpretará erróneamente como un separador de columna, rompiendo la estructura del registro.

### B. Formato CSV Estándar y el Método `parseCsv()`
El formato CSV (*Comma-Separated Values*) utiliza comas `,` como separador, pero cuenta con reglas estándar para proteger comas y comillas dentro de los datos:

| Caso en CSV | Regla de Representación Estándar | Ejemplo |
| :--- | :--- | :--- |
| **Coma dentro de un campo** | Se encierra todo el campo entre comillas dobles `"..."`. | `"García, Luis"` |
| **Comilla literal dentro de un campo entrecomillado** | Se duplica la comilla escribiendo dos comillas consecutivas `""`. | `"Monitor ""Pro"" 24"` |

* **¿Por qué falla `split(",")` en CSV?** Porque divide tanto por las comas separadoras de columnas como por las comas internas protegidas entre comillas.
* **Funcionamiento de `parseCsv()`:**
  * Recorre la línea carácter a carácter manteniendo un estado booleano **`entreComillas`**.
  * La coma `,` actúa como separador de campo **únicamente** cuando `entreComillas` es `false`.
  * Al terminar el bucle de lectura de caracteres, se añade el contenido acumulado en `actual` (`StringBuilder`) a la lista `campos` **para conservar el último campo** de la línea (ya que el último campo no va seguido de una coma).

---

## 4. Serialización con Jackson: JSON y XML

**Serializar** un objeto Java consiste en convertir su estado en memoria a una representación persistible o transmisible (como texto JSON o XML). **Deserializar** es el proceso inverso: reconstruir los objetos Java a partir de dicho formato.

### Esquema Comparativo de Jackson en el Proyecto

```text
                         ┌──> JSON (ObjectMapper)
                         │     ├── Exportar: mapper.writeValue(archivo, listaClientes)
                         │     └── Importar: mapper.readValue(archivo, new TypeReference<List<Cliente>>() {})
                         │
List<Cliente> (Java) ────┤
                         │
                         └──> XML (XmlMapper + clase envoltorio ClientesXml)
                               ├── Exportar: xmlMapper.writeValue(archivo, new ClientesXml(listaClientes))
                               └── Importar: ClientesXml contenedor = xmlMapper.readValue(archivo, ClientesXml.class)
```

### A. Procesamiento de JSON (`ObjectMapper`)
* **Sintaxis JSON:**
  * Los objetos se encierran entre llaves `{ ... }`.
  * Las listas o arrays se encierran entre corchetes `[ ... ]`.
  * Los valores booleanos válidos son los literales en minúsculas **`true`** y **`false`** (sin comillas).
* **Formateo legible (`INDENT_OUTPUT`):** Configurar `SerializationFeature.INDENT_OUTPUT` en `ObjectMapper` formatea la salida JSON con saltos de línea y sangrías para facilitar su lectura humana, sin alterar los datos.
* **Escritura y Lectura:**
  * `mapper.writeValue(File, objeto)`: Serializa el objeto Java y lo guarda en el archivo.
  * `mapper.readValue(...)`: Deserializa el archivo JSON. Si el JSON está mal formado (sintaxis inválida o tipos incompatibles), Jackson lanza una excepción de procesamiento (`JsonProcessingException` / `IOException`).
* **Uso de `TypeReference<List<Cliente>>`:** En Java, debido al borrado de tipos genéricos (*type erasure*) en tiempo de ejecución, `List<Cliente>.class` no existe. `TypeReference` conserva la información completa del tipo genérico para que Jackson sepa instanciar objetos `Cliente` dentro de la lista.

### B. Procesamiento de XML (`XmlMapper` y `ClientesXml`)
* **Sintaxis XML:** A diferencia de JSON, XML estructura la información mediante **etiquetas de apertura y cierre** (ejemplo: `<cliente><nombre>Ana</nombre></cliente>`), atributos y elementos anidados bajo un único elemento raíz.
* **`XmlMapper`:** Clase de la librería `jackson-dataformat-xml` equivalente a `ObjectMapper`, especializada en serializar y deserializar XML.
* **Clase envoltorio `ClientesXml`:** XML exige un elemento raíz único que englobe la colección. `ClientesXml` actúa como contenedor de `List<Cliente>` proporcionando una etiqueta raíz predecible (ej. `<clientes>`).
* **Deserialización tipada:** Al ejecutar `XML_MAPPER.readValue(archivo, ClientesXml.class)`, el literal `.class` pasa a Jackson la información de tipo de la clase destino (`Class<ClientesXml>`), devolviendo un objeto de tipo `ClientesXml`.

---

# PARTE II: CUESTIONARIO RESUELTO Y COMENTADO (30 PREGUNTAS)

1. **¿Qué clase permite leer un fichero de texto línea a línea?**
   * **Respuesta correcta:** `BufferedReader`
   * **Explicación:** `BufferedReader` dispone del método `readLine()`, que devuelve una línea de texto o `null` al llegar al final del fichero.

2. **¿Qué devuelve `readLine()` cuando termina el fichero?**
   * **Respuesta correcta:** `null`
   * **Explicación:** `readLine()` devuelve `null` al alcanzar el fin del flujo (EOF); una línea vacía devuelve `""`.

3. **¿Qué método escribe un salto de línea con `BufferedWriter`?**
   * **Respuesta correcta:** `newLine()`
   * **Explicación:** `newLine()` escribe el separador de línea apropiado para el sistema operativo en ejecución.

4. **¿Qué ventaja aporta `try-with-resources` al trabajar con ficheros?**
   * **Respuesta correcta:** Cierra recursos automáticamente.
   * **Explicación:** Todos los recursos `AutoCloseable` declarados en el `try` se cierran automáticamente al finalizar, incluso si se produce una excepción.

5. **¿Qué codificación conviene especificar para intercambiar texto?**
   * **Respuesta correcta:** `UTF-8`
   * **Explicación:** `UTF-8` admite caracteres internacionales y evita problemas de compatibilidad al no depender de la codificación predeterminada del sistema.

6. **¿Qué representa un objeto `Path` en Java NIO.2?**
   * **Respuesta correcta:** Una ruta del sistema de ficheros.
   * **Explicación:** `Path` modela la ubicación de un archivo o directorio, pero no abre por sí mismo ningún flujo de lectura o escritura.

7. **¿Qué ocurre al usar `Files.newBufferedWriter` sin `APPEND` sobre un fichero existente?**
   * **Respuesta correcta:** Se trunca su contenido anterior.
   * **Explicación:** Por defecto, `newBufferedWriter` crea el fichero si no existe o vacía (trunca) su contenido previo si ya existía; `StandardOpenOption.APPEND` cambia este comportamiento para añadir al final.

8. **¿Qué excepción es típica de una operación fallida de entrada/salida?**
   * **Respuesta correcta:** `IOException`
   * **Explicación:** `IOException` es la excepción comprobada estándar para errores en operaciones de entrada/salida con ficheros o flujos.

9. **¿Qué problema aparece al separar con `split(",")` un CSV con campos entrecomillados?**
   * **Respuesta correcta:** Divide también comas internas.
   * **Explicación:** `split(",")` no distingue entre las comas que separan columnas y las comas que forman parte del contenido dentro de comillas.

10. **¿Cómo se representa una coma dentro de un campo CSV estándar?**
    * **Respuesta correcta:** Encerrando el campo entre comillas.
    * **Explicación:** Todo campo que contenga el carácter delimitador (coma) o saltos de línea se encierra entre comillas dobles `"..."` para preservar su contenido íntegro.

11. **¿Cómo se representa una comilla literal dentro de un campo CSV entrecomillado?**
    * **Respuesta correcta:** Con dos comillas consecutivas.
    * **Explicación:** En el estándar CSV (RFC 4180), una comilla doble literal dentro de un campo entrecomillado se escapa escribiendo `""`.

12. **¿Para qué sirve la variable `entreComillas` en `parseCsv()`?**
    * **Respuesta correcta:** Distinguir comas internas y separadoras.
    * **Explicación:** Permite saber si el analizador está dentro o fuera de un bloque entrecomillado, de modo que la coma solo divida campos cuando `entreComillas` es `false`.

13. **¿Por qué se añade `actual` a `campos` al terminar el bucle de `parseCsv()`?**
    * **Respuesta correcta:** Para conservar el último campo.
    * **Explicación:** Como el último campo de una línea CSV no termina con coma, permanece acumulado en el `StringBuilder` (`actual`) y debe añadirse manualmente al finalizar el recorrido.

14. **¿Qué estructura representa una lista de objetos en JSON?**
    * **Respuesta correcta:** Un array entre corchetes.
    * **Explicación:** JSON representa las listas o colecciones ordenadas mediante corchetes `[ ... ]` y los objetos mediante llaves `{ ... }`.

15. **¿Cuál de estos valores es un booleano válido en JSON?**
    * **Respuesta correcta:** `true`
    * **Explicación:** La especificación JSON exige escribir los literales booleanos `true` y `false` en minúsculas y sin comillas.

16. **¿Qué método de Jackson escribe un objeto Java en un fichero JSON?**
    * **Respuesta correcta:** `writeValue()`
    * **Explicación:** `ObjectMapper.writeValue(File, objeto)` serializa el objeto Java y lo escribe directamente en el archivo especificado.

17. **¿Qué método de Jackson recupera objetos Java desde un fichero JSON?**
    * **Respuesta correcta:** `readValue()`
    * **Explicación:** `ObjectMapper.readValue()` lee y deserializa el contenido JSON convirtiéndolo en instancias de clases Java.

18. **¿Por qué se usa `TypeReference<List<Cliente>>` al importar JSON?**
    * **Respuesta correcta:** Para conservar el tipo genérico de los elementos.
    * **Explicación:** Proporciona a Jackson la información exacta del tipo genérico (`List<Cliente>`), superando el borrado de tipos (*type erasure*) de Java en tiempo de ejecución.

19. **¿Qué efecto tiene `INDENT_OUTPUT` en `ObjectMapper`?**
    * **Respuesta correcta:** Formatea el JSON con sangrías.
    * **Explicación:** Genera una salida indentada y con saltos de línea legible para personas, sin modificar la estructura ni el significado de los datos.

20. **¿Qué significa serializar un objeto Java?**
    * **Respuesta correcta:** Convertirlo a una representación persistible.
    * **Explicación:** Serializar consiste en transformar el estado de un objeto en memoria a un formato (JSON, XML, bytes, texto) que pueda almacenarse en disco o transmitirse.

21. **¿Qué característica distingue XML de JSON en su sintaxis habitual?**
    * **Respuesta correcta:** XML utiliza etiquetas de apertura y cierre.
    * **Explicación:** XML estructura la información jerárquicamente mediante etiquetas (`<etiqueta>...</etiqueta>`), atributos y contenido textual.

22. **¿Qué clase de Jackson se utiliza en el proyecto para procesar XML?**
    * **Respuesta correcta:** `XmlMapper`
    * **Explicación:** `XmlMapper`, perteneciente al módulo `jackson-dataformat-xml`, permite serializar y deserializar objetos Java a XML.

23. **¿Qué función cumple `ClientesXml` en la importación XML del proyecto?**
    * **Respuesta correcta:** Envolver la lista bajo una raíz XML.
    * **Explicación:** Proporciona un objeto contenedor con una lista de clientes y un elemento raíz XML único y predecible.

24. **¿Qué se obtiene con `XML_MAPPER.readValue(archivo, ClientesXml.class)`?**
    * **Respuesta correcta:** Un objeto del tipo `ClientesXml`.
    * **Explicación:** El segundo argumento indica a Jackson la clase destino en la que debe deserializar el contenido del fichero XML.

25. **¿Qué expresa `ClientesXml.class` en una llamada a Jackson?**
    * **Respuesta correcta:** La información de tipo de la clase.
    * **Explicación:** El literal `.class` devuelve el objeto `Class<ClientesXml>` con los metadatos del tipo, sin crear una instancia nueva.

26. **¿Qué ocurre si el JSON está mal formado durante `readValue()`?**
    * **Respuesta correcta:** Puede lanzarse una excepción de procesamiento.
    * **Explicación:** Jackson detecta errores de sintaxis o de mapeo de tipos y lanza una excepción (`JsonProcessingException` / `IOException`).

27. **¿Qué representa una línea TXT con campos separados por punto y coma?**
    * **Respuesta correcta:** Un registro delimitado por campos.
    * **Explicación:** En el formato TXT del proyecto, cada línea representa una entidad con sus atributos separados por `;`.

28. **¿Qué riesgo tiene usar `split(";")` sin más para importar TXT?**
    * **Respuesta correcta:** Falla si un campo contiene el delimitador.
    * **Explicación:** Si algún dato de texto incluye un `;`, `split(";")` lo dividirá como si fuera una columna adicional, alterando el orden o provocando errores de conversión.

29. **¿Qué hace `Integer.parseInt` al importar el ID desde texto?**
    * **Respuesta correcta:** Convierte un `String` en `int`.
    * **Explicación:** Transforma una cadena numérica en un entero primitivo `int` (lanzando `NumberFormatException` si el formato no es válido).

30. **¿Qué se recomienda hacer antes de reemplazar la lista visible con datos importados?**
    * **Respuesta correcta:** Validar la importación y sus resultados.
    * **Explicación:** Evita que un error de lectura o un archivo corrupto borre los datos actuales de la interfaz sustituyéndolos por una lista vacía o incompleta.