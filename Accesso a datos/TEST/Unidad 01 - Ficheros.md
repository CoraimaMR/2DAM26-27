# Apuntes de Acceso a Datos: Manejo de Ficheros en Java

Estos apuntes resumen los conceptos clave, clases, métodos y buenas prácticas sobre el tratamiento de ficheros planos (TXT, CSV) y estructurados (JSON, XML) en Java mediante **Java NIO.2** y la librería **Jackson**.

\---

## 1\. Flujos de Texto y Java NIO.2

### Clases y Métodos Básicos de Lectura y Escritura

* **Lectura línea a línea (`BufferedReader`):** Clase utilizada para leer texto de un flujo de entrada de caracteres de manera eficiente almacenando en búfer.

  * El método `readLine()` lee una línea de texto y devuelve `null` cuando se alcanza el final del fichero (EOF).
* **Escritura con búfer (`BufferedWriter`):** Escribe texto de forma eficiente.

  * El método `newLine()` escribe un salto de línea adaptado al sistema operativo correspondiente.
* **Representación de rutas (`Path`):** En la API **Java NIO.2** (`java.nio.file`), un objeto `Path` representa una ruta dentro del sistema de ficheros.
* **Apertura de escritura (`Files.newBufferedWriter`):** Si se invoca sin la opción `StandardOpenOption.APPEND` sobre un fichero que ya existe, **se trunca su contenido anterior** (se sobrescribe desde cero).

### Buenas Prácticas y Control de Errores

* **Gestión automática de recursos (`try-with-resources`):** Garantiza que los recursos abiertos (como flujos de lectura o escritura que implementan `AutoCloseable`) **se cierren automáticamente** al finalizar el bloque, evitando fugas de memoria o bloqueos de archivos.
* **Codificación estándar:** Se recomienda especificar siempre **`UTF-8`** al leer o escribir texto para garantizar la compatibilidad e intercambio correcto de caracteres especiales entre distintos sistemas.
* **Manejo de excepciones (`IOException`):** Es la excepción comprobada (*checked exception*) típica que se lanza cuando ocurre un error durante una operación de entrada/salida (ej. fichero no encontrado, falta de permisos o disco lleno).

\---

## 2\. Ficheros Delimitados: TXT y CSV

### Ficheros de Texto Delimitados (TXT)

* Una línea con datos separados por punto y coma (ej. `1;Juan;juan@mail.com`) representa un **registro delimitado por campos**.
* **Riesgo de `split(";")`:** Utilizar `split(";")` directamente sin validaciones previas **falla si algún campo contiene el propio carácter delimitador** (`;`) en su interior, desajustando las columnas.
* **Conversión de tipos (`Integer.parseInt()`):** Al leer datos desde texto, todos los valores entran como cadenas (`String`). `Integer.parseInt()` convierte un `String` numérico en un tipo primitivo `int` (por ejemplo, para recuperar el ID).

### Estándar CSV (*Comma-Separated Values*)

* **Limitación de `split(",")`:** Si un campo contiene comas internas (ej. `"Calle Mayor, 12"`), `split(",")` **divide también las comas internas**, rompiendo la estructura del registro.
* **Escape de caracteres en CSV estándar:**

  * **Coma dentro de un campo:** Se representa **encerrando todo el campo entre comillas dobles** (`"valor, con coma"`).
  * **Comilla literal dentro de un campo entrecomillado:** Se representa **con dos comillas consecutivas** (`""`).

### Lógica de un Parser Manual (`parseCsv()`)

* **Variable de estado (`entreComillas`):** Booleano utilizado mientras se recorre la línea carácter a carácter para **distinguir entre comas internas** (dentro de comillas, que forman parte del dato) **y comas separadoras** (fuera de comillas, que delimitan columnas).
* **Al finalizar el bucle:** Se debe añadir el acumulador `actual` a la lista `campos` **para conservar el último campo** de la línea, ya que tras el último valor no suele haber una coma que dispare su inserción.

\---

## 3\. Serialización y Procesamiento de JSON (Jackson)

### Conceptos de JSON y Serialización

* **Serializar un objeto Java:** Consiste en **convertirlo a una representación persistible** (como una cadena JSON, XML o secuencia de bytes) para poder almacenarlo en un fichero o transmitirlo por red.
* **Sintaxis básica de JSON:**

  * Una **lista de objetos** se representa mediante un **array entre corchetes** (`\[ { ... }, { ... } ]`).
  * Los valores **booleanos válidos** deben escribirse estrictamente en minúsculas: `true` o `false`.

### Uso de `ObjectMapper` (Librería Jackson)

* **Exportar (Escribir JSON):** El método `writeValue()` serializa un objeto Java y lo escribe directamente en un fichero o flujo de salida.
* **Importar (Leer JSON):** El método `readValue()` deserializa el contenido de un fichero JSON para reconstruir objetos Java.

  * Si el fichero JSON está mal formado o tiene errores de sintaxis, `readValue()` **puede lanzar una excepción de procesamiento** (como `JsonProcessingException`).
* **Preservación de tipos genéricos (`TypeReference<List<Cliente>>`):** Debido al borrado de tipos (*type erasure*) en Java, se utiliza `TypeReference` al importar listas para **conservar el tipo genérico de los elementos** (`Cliente`) en tiempo de ejecución.
* **Formato legible (`SerializationFeature.INDENT\_OUTPUT`):** Configura el `ObjectMapper` para que **formatee el JSON generado con sangrías y saltos de línea** (*pretty-print*).

\---

## 4\. Procesamiento de XML (Jackson XML)

* **Diferencia sintáctica frente a JSON:** Mientras JSON usa pares clave-valor entre llaves y corchetes, **XML utiliza etiquetas de apertura y cierre** (`<cliente>...</cliente>`).
* **Clase principal (`XmlMapper`):** Extensión de `ObjectMapper` en Jackson utilizada específicamente para serializar y deserializar documentos XML.
* **Clase envoltorio (`ClientesXml`):** En XML todo documento requiere un único elemento raíz. La clase `ClientesXml` cumple la función de **envolver la lista de clientes bajo una etiqueta raíz XML** (por ejemplo, `<clientes><cliente>...</cliente></clientes>`).
* **Deserialización XML:**

  * La llamada `XML\_MAPPER.readValue(archivo, ClientesXml.class)` devuelve **un objeto del tipo `ClientesXml`** con la lista cargada.
  * El parámetro `ClientesXml.class` pasa a Jackson **la información de tipo de la clase** (*class literal* o metadatos de reflexión) necesaria para instanciarla y mapear sus atributos.

\---

## 5\. Buenas Prácticas de Integridad en la Importación

* Antes de reemplazar los datos actuales en memoria o en la interfaz de usuario (lista visible) con los registros recién importados desde un fichero, se recomienda **validar la importación y sus resultados** (comprobar que no hubo errores de lectura, que la lista no sea nula o corrupta) para evitar pérdida de datos en la aplicación.

\---

## Tabla Resumen de Respuestas del Cuestionario

|Pregunta|Concepto / Pregunta|Respuesta Correcta|
|-|-|-|
|**1**|Clase para leer texto línea a línea|`BufferedReader`|
|**2**|Retorno de `readLine()` al final del fichero|`null`|
|**3**|Método de salto de línea en `BufferedWriter`|`newLine()`|
|**4**|Ventaja de `try-with-resources`|Cierra recursos automáticamente|
|**5**|Codificación recomendada para texto|`UTF-8`|
|**6**|Qué representa `Path` en NIO.2|Una ruta del sistema de ficheros|
|**7**|`Files.newBufferedWriter` sin `APPEND`|Se trunca su contenido anterior|
|**8**|Excepción típica de E/S|`IOException`|
|**9**|Problema de `split(",")` con comillas|Divide también comas internas|
|**10**|Representar coma interna en CSV|Encerrando el campo entre comillas|
|**11**|Representar comilla literal en CSV|Con dos comillas consecutivas|
|**12**|Uso de `entreComillas` en `parseCsv()`|Distinguir comas internas y separadoras|
|**13**|Añadir `actual` al final de `parseCsv()`|Para conservar el último campo|
|**14**|Lista de objetos en JSON|Un array entre corchetes|
|**15**|Booleano válido en JSON|`true`|
|**16**|Método Jackson para escribir JSON|`writeValue()`|
|**17**|Método Jackson para leer JSON|`readValue()`|
|**18**|Uso de `TypeReference<List<Cliente>>`|Para conservar el tipo genérico de los elementos|
|**19**|Efecto de `INDENT\_OUTPUT`|Formatea el JSON con sangrías|
|**20**|Significado de serializar un objeto|Convertirlo a una representación persistible|
|**21**|Característica distintiva de XML|XML utiliza etiquetas de apertura y cierre|
|**22**|Clase Jackson para XML|`XmlMapper`|
|**23**|Función de `ClientesXml`|Envolver la lista bajo una raíz XML|
|**24**|Resultado de `XML\_MAPPER.readValue(...)`|Un objeto del tipo `ClientesXml`|
|**25**|Qué expresa `ClientesXml.class`|La información de tipo de la clase|
|**26**|Si el JSON está mal formado en `readValue()`|Puede lanzarse una excepción de procesamiento|
|**27**|Línea TXT separada por `;`|Un registro delimitado por campos|
|**28**|Riesgo de `split(";")` en TXT|Falla si un campo contiene el delimitador|
|**29**|Función de `Integer.parseInt`|Convierte un `String` en `int`|
|**30**|Acción previa a reemplazar lista visible|Validar la importación y sus resultados|



