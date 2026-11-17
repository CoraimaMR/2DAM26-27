# Apuntes de Desarrollo Android con Kotlin

Estos apuntes resumen los conceptos esenciales sobre diseño de interfaces en XML (`LinearLayout` y `ConstraintLayout`), fundamentos del lenguaje **Kotlin** y gestión de **Activities**, **Intents** y eventos en Android.

\---

## 1\. Diseño de Interfaces en Android (Layouts y Vistas XML)

### Contenedores Principales

* **`LinearLayout`:** Contenedor que organiza sus elementos hijos en una **única fila o columna**.

  * El atributo `android:orientation` determina su dirección (`horizontal` o `vertical`).
  * El atributo `android:gravity` alinea o centra los elementos hijos dentro del propio contenedor.
* **`ConstraintLayout`:** Contenedor flexible utilizado principalmente para **definir restricciones** (*constraints*) relativas entre vistas y respecto al contenedor padre, evitando jerarquías anidadas.

  * Una anchura de `0dp` (*match constraints*) con restricciones aplicadas a ambos lados significa que la vista debe **ajustarse al espacio delimitado por dichas restricciones**.
  * Ejemplo de restricción vertical: `app:layout\_constraintTop\_toBottomOf` conecta el borde superior de una vista con el borde inferior de otra.

### Dimensiones, Espaciado y Unidades

* **Dimensiones de vistas (`layout\_width` / `layout\_height`):**

  * `match\_parent`: Permite que una vista ocupe todo el espacio disponible de su contenedor padre.
  * `wrap\_content`: Ajusta el tamaño de la vista exactamente al tamaño de su contenido.
* **Espaciado interior y exterior:**

  * `android:padding`: Establece el **espacio interior** entre el borde del contenedor/vista y su contenido.
  * `android:layout\_margin`: Añade **separación exterior** alrededor de una vista respecto a otras vistas o al borde del padre.
* **Unidades de medida:**

  * **`sp` (*Scale-independent Pixels*):** Unidad recomendada para definir el **tamaño del texto**, ya que escala según las preferencias de tamaño de fuente del usuario.
  * **`dp` (*Density-independent Pixels*):** Unidad estándar para dimensiones, márgenes y rellenos de componentes.
* **Identificadores de vistas:**

  * Para asignar un identificador nuevo a una vista en XML se utiliza la sintaxis `android:id="@+id/nombre\_vista"`.

\---

## 2\. Fundamentos del Lenguaje Kotlin

### Variables, Tipos y Seguridad ante Nulos (*Null Safety*)

* **Inmutabilidad vs. Mutabilidad:**

  * `val`: Declara una referencia de solo lectura que **no puede reasignarse** una vez inicializada.
  * `var`: Declara una variable mutable que **permite reasignar** su valor.
* **Tipos de datos básicos:**

  * `Boolean`: Representa un valor lógico verdadero (`true`) o falso (`false`).
* **Tipos anulables (*Nullable*):**

  * Añadir el símbolo `?` después de un tipo (ej. `String?`) indica que la variable **admite valores `null`**.

### Funciones, Operadores y Estructuras de Control

* **Declaración de funciones:** Se utiliza la palabra clave `fun`.
* **Operadores:**

  * `||`: Operador lógico que representa la condición **«O»** (*OR* lógico).
  * `==`: Comprueba la **igualdad estructural de valores** (equivalente a `equals()`).
* **Control de flujo:**

  * `if`: Estructura condicional que ejecuta instrucciones únicamente cuando se cumple una condición booleana.
* **Métodos comunes de cadenas y conversión:**

  * `toString()`: Convierte habitualmente un objeto o valor a su representación textual (`String`).
  * `trim()`: Elimina los espacios en blanco al inicio y al final de una cadena de texto.

\---

## 3\. Activities, Eventos e Intents en Android

### Vinculación de Interfaz y Eventos

* **Cargar la interfaz:** El método `setContentView()` (llamado habitualmente dentro de `onCreate()`) relaciona una `Activity` con su archivo de diseño XML.
* **Referenciar vistas:** El método `findViewById()` permite obtener en código Kotlin una referencia a una vista definida en el XML mediante su ID.
* **Gestión de clics:** El método `setOnClickListener()` asocia una acción o bloque de código a la pulsación de un botón (`Button`) u otra vista.
* **Avisos temporales:** El componente **`Toast`** permite mostrar un mensaje breve y flotante en pantalla durante unos segundos sin interrumpir la interacción del usuario.

### Ciclo de Vida y Navegación entre Activities

* **Creación de la pantalla:** El método `onCreate()` del ciclo de vida se ejecuta al crear por primera vez una `Activity` (donde se inicializa la UI y las variables).
* **Navegación y paso de datos (`Intent`):**

  * **`Intent`:** Objeto de mensajería utilizado habitualmente para solicitar la apertura de otra `Activity` (u otro componente de Android).
  * `putExtra(clave, valor)`: Añade un dato identificado por una clave dentro de un `Intent` para enviarlo a la pantalla destino.
  * `startActivity(intent)`: Inicia la nueva `Activity` a partir del `Intent` configurado.

\---

## Tabla Resumen de Respuestas del Cuestionario

|Pregunta|Concepto / Pregunta|Respuesta Correcta|
|-|-|-|
|**1**|Contenedor que organiza en una única fila o columna|`LinearLayout`|
|**2**|Atributo que determina la orientación de `LinearLayout`|`orientation`|
|**3**|Valor para ocupar todo el espacio disponible del padre|`match\_parent`|
|**4**|Valor que ajusta el tamaño de una vista a su contenido|`wrap\_content`|
|**5**|Propiedad para el espacio interior de un contenedor|`padding`|
|**6**|Atributo para separación exterior alrededor de una vista|`layout\_margin`|
|**7**|Uso principal de `ConstraintLayout`|Para definir restricciones|
|**8**|Anchura `0dp` con restricciones a ambos lados|Ajustar a restricciones|
|**9**|Atributo que centra elementos hijos en `LinearLayout`|`gravity`|
|**10**|Unidad recomendada para tamaño de texto|`sp`|
|**11**|Conectar parte superior de una vista con inferior de otra|`layout\_constraintTop\_toBottomOf`|
|**12**|Atributo para asignar un ID nuevo en XML|`android:id="@+id/..."`|
|**13**|Palabra clave en Kotlin para referencia no reasignable|`val`|
|**14**|Palabra clave en Kotlin para variable reasignable|`var`|
|**15**|Tipo de Kotlin para verdadero o falso|`Boolean`|
|**16**|Método que convierte un valor a texto|`toString()`|
|**17**|Operación que elimina espacios al inicio y final|`trim()`|
|**18**|Operador lógico «O» en Kotlin|`\|\|`|
|**19**|Estructura que ejecuta código según una condición|`if`|
|**20**|Qué indica `?` después de un tipo en Kotlin|Que admite `null`|
|**21**|Palabra clave para declarar una función en Kotlin|`fun`|
|**22**|Operador que comprueba igualdad de valores en Kotlin|`==`|
|**23**|Método para asociar acción al pulsar un botón|`setOnClickListener()`|
|**24**|Método que relaciona una `Activity` con su XML|`setContentView()`|
|**25**|Método para obtener referencia a una vista por su ID|`findViewById()`|
|**26**|Objeto para solicitar apertura de otra `Activity`|`Intent`|
|**27**|Método que inicia una `Activity` desde un `Intent`|`startActivity()`|
|**28**|Método que añade un dato con clave a un `Intent`|`putExtra()`|
|**29**|Método del ciclo de vida al crear una `Activity`|`onCreate()`|
|**30**|Componente para mostrar aviso breve y temporal|`Toast`|



