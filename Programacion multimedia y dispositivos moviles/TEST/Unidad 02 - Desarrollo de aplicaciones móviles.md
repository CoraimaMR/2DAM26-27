# Guía Completa PMDM: Desarrollo Android con Kotlin y XML (Proyecto SportTracking)

Este documento reúne la teoría esencial basada en la estructura real del proyecto Android (`MainActivity.kt`, `SegundaActivity.kt`, `activity_main.xml`, `activity_segunda.xml`, `activity_producto.xml` y `AndroidManifest.xml`), junto con el cuestionario de 30 preguntas resuelto y comentado para repaso.

---

# PARTE I: APUNTES Y ESQUEMAS DEL PROYECTO

## 1. Arquitectura General de una App Android (`SportTracking`)

Una aplicación Android clásica basada en Vistas (XML) separa el diseño de la interfaz gráfica de la lógica de comportamiento escrita en Kotlin.

```text
+-----------------------------------+       setContentView()       +-----------------------------------+
|         Capa de Lógica            | ---------------------------> |         Capa de Interfaz          |
|   MainActivity.kt / Segunda...    |                              |    activity_main.xml / ...xml     |
|                                   | <--------------------------- |                                   |
|  - findViewById<T>(R.id.vista)    |      Interacción Usuario     |  - ConstraintLayout / Linear...   |
|  - setOnClickListener { ... }     |                              |  - TextView, EditText, Button...  |
|  - Intent + putExtra()            |                              |  - android:id="@+id/..."          |
+-----------------------------------+                              +-----------------------------------+
                  |
                  v  startActivity(intent)
+-----------------------------------+
|         SegundaActivity.kt        |
|  - intent.getStringExtra("clave") |
|  - Toast.makeText(...).show()     |
+-----------------------------------+
```

* **`AndroidManifest.xml`:** Archivo de configuración global donde se declaran todas las `Activity` de la aplicación y cuál es la actividad principal de arranque (`MAIN` / `LAUNCHER`).
* **`res/layout/*.xml`:** Ficheros XML que definen la jerarquía visual de cada pantalla.
* **`java/com/example/sporttracking/*.kt`:** Clases Kotlin (`AppCompatActivity`) que controlan el ciclo de vida, eventos y navegación entre pantallas.

---

## 2. Diseño de Interfaces XML: Contenedores, Dimensiones y Unidades

### A. Contenedores Principales (`ViewGroup`)

| Contenedor | Comportamiento y Uso Principal |
| :--- | :--- |
| **`LinearLayout`** | Organiza sus vistas hijas en **una única fila o columna**, según el atributo `android:orientation` (`"horizontal"` o `"vertical"`). |
| **`ConstraintLayout`** | Posiciona y dimensiona vistas mediante **restricciones (relaciones)** respecto a otras vistas o respecto al contenedor padre, evitando anidar múltiples layouts. |

### B. Atributos de Tamaño, Espaciado y Alineación

```text
+----------------- Contenedor Padre (match_parent) -----------------+
|                                                                   |
|   <-- layout_margin (separación EXTERIOR respecto al borde) -->   |
|   +-------------------- Vista / Contenedor -------------------+   |
|   |  <-- padding (espacio INTERIOR respecto al contenido) --> |   |
|   |  +-----------------------------------------------------+  |   |
|   |  |  Contenido (ajustado con wrap_content o 0dp)        |  |   |
|   |  +-----------------------------------------------------+  |   |
|   +-----------------------------------------------------------+   |
+-------------------------------------------------------------------+
```

* **Dimensiones (`layout_width` / `layout_height`):**
  * **`match_parent`:** La vista ocupa todo el espacio disponible de su contenedor padre.
  * **`wrap_content`:** Calcula automáticamente el tamaño mínimo necesario para mostrar su contenido.
  * **`0dp` (en `ConstraintLayout`):** Activa el modo *match constraints*, ajustando el tamaño exactamente al espacio delimitado por las restricciones opuestas (por ejemplo, izquierda y derecha).
* **Espaciados:**
  * **`android:padding`:** Añade espacio **interior** entre los bordes del contenedor y su propio contenido.
  * **`android:layout_margin`:** Añade separación **exterior** alrededor de la vista respecto a otros elementos o bordes del padre.
* **Alineación y Restricciones:**
  * **`android:gravity`:** Controla la colocación o centrado de los elementos **hijos dentro** del contenedor (por ejemplo, centrar contenido dentro de un `LinearLayout`).
  * **`app:layout_constraintTop_toBottomOf`:** Conecta el borde superior de una vista con el borde inferior de otra vista de referencia.
* **Identificadores y Unidades:**
  * **`android:id="@+id/nombre_vista"`:** Declara un identificador nuevo (`@+id/`) en el recurso `R.id` para poder referenciar la vista desde XML o Kotlin.
  * **`sp` (*Scale-independent Pixels*):** Unidad recomendada para tamaños de texto (`android:textSize`), ya que respeta la escala de fuente configurada por el usuario en el dispositivo (a diferencia de `dp`, que se usa para márgenes y tamaños de vistas).

---

## 3. Fundamentos de Kotlin en Android

### Variables, Nulabilidad y Funciones

```kotlin
// 1. Declaración de variables
val nombreApp = "SportTracking"   // Referencia inmutable (no puede reasignarse)
var contador: Int = 0             // Variable mutable (permite reasignar valores)

// 2. Nulabilidad (Null Safety)
var textoOpcional: String? = null // El símbolo '?' indica que admite null

// 3. Declaración de funciones y estructuras de control
fun validarEntrada(texto: String): Boolean {
    val limpio = texto.trim()     // Elimina espacios al inicio y al final
    if (limpio == "" || limpio == "error") { // '||' es el operador lógico O; '==' compara valores
        return false
    }
    return true
}
```

### Resumen de Elementos Clave de Kotlin

| Elemento Kotlin | Significado / Uso |
| :--- | :--- |
| **`val`** | Declara una referencia de solo lectura que **no puede reasignarse** tras su inicialización. |
| **`var`** | Declara una variable mutable que **permite reasignar** nuevos valores. |
| **`fun`** | Palabra clave utilizada para declarar una función. |
| **`Boolean`** | Tipo de dato lógico que representa `true` o `false`. |
| **`Tipo?`** (ej. `String?`) | Indica que la variable de ese tipo **admite valores `null`**. |
| **`==`** | Operador de comparación de **igualdad de valores** (mientras que `=` es asignación). |
| **`\|\|`** | Operador lógico **«O» (OR)**: devuelve `true` si al menos una de las condiciones es verdadera. |
| **`if`** | Estructura condicional que ejecuta un bloque de código solo cuando se cumple una condición booleana. |
| **`.toString()`** | Método que obtiene la representación textual (`String`) de un valor u objeto. |
| **`.trim()`** | Operación sobre `String` que elimina espacios en blanco al inicio y al final del texto. |

---

## 4. Activities, Ciclo de Vida, Eventos e Intents

### Flujo de Inicialización y Navegación entre Pantallas

```text
[Arranque MainActivity]
         │
         ▼
   onCreate() ──> setContentView(R.layout.activity_main)  [Vincula el XML]
         │
         ▼
   val btn = findViewById<Button>(R.id.btnIniciar)        [Obtiene referencia a la vista]
         │
         ▼
   btn.setOnClickListener {                               [Escucha pulsación del botón]
       val intent = Intent(this, SegundaActivity::class.java)
       intent.putExtra("USUARIO", "Ramiro")               [Adjunta datos clave-valor]
       startActivity(intent)                              [Lanza la nueva Activity]
   }
```

### Componentes Esenciales del Framework Android

* **`onCreate()`:** Método principal del ciclo de vida que se ejecuta al crear la `Activity`; es el punto habitual de inicialización.
* **`setContentView(R.layout.activity_main)`:** Asocia la `Activity` con su archivo de interfaz XML para mostrarlo en pantalla.
* **`findViewById(R.id.id_vista)`:** Localiza una vista dentro de la jerarquía XML mediante su identificador para manipularla desde Kotlin.
* **`setOnClickListener { ... }`:** Registra el bloque de código que se ejecutará cuando el usuario pulse un botón o vista.
* **`Intent`:** Objeto mensajero utilizado para solicitar la apertura de otra `Activity` (mediante un `Intent` explícito).
* **`putExtra("clave", valor)`:** Añade datos identificados por una clave al `Intent` antes de enviarlo.
* **`startActivity(intent)`:** Inicia la `Activity` especificada en el `Intent`.
* **`Toast`:** Componente ligero que muestra un aviso breve y temporal en pantalla sin interrumpir ni sustituir la `Activity` actual (`Toast.makeText(this, "Mensaje", Toast.LENGTH_SHORT).show()`).

---

# PARTE II: CUESTIONARIO RESUELTO Y COMENTADO (30 PREGUNTAS)

1. **¿Qué contenedor organiza sus elementos en una única fila o columna?**
   * **Respuesta correcta:** `LinearLayout`
   * **Explicación:** `LinearLayout` distribuye sus elementos hijos secuencialmente en una sola fila o columna según la orientación configurada.

2. **¿Qué atributo determina la orientación de un `LinearLayout`?**
   * **Respuesta correcta:** `orientation`
   * **Explicación:** `android:orientation` establece si la distribución de elementos es horizontal (`horizontal`) o vertical (`vertical`).

3. **¿Qué valor permite que una vista ocupe el espacio disponible de su padre?**
   * **Respuesta correcta:** `match_parent`
   * **Explicación:** `match_parent` adapta la dimensión (ancho o alto) de la vista al tamaño disponible de su contenedor padre.

4. **¿Qué valor ajusta el tamaño de una vista a su contenido?**
   * **Respuesta correcta:** `wrap_content`
   * **Explicación:** `wrap_content` calcula automáticamente la dimensión mínima necesaria para mostrar el contenido de la vista.

5. **¿Qué propiedad establece el espacio interior de un contenedor?**
   * **Respuesta correcta:** `padding`
   * **Explicación:** `padding` separa el contenido interno de los bordes interiores del propio contenedor o vista.

6. **¿Qué atributo añade separación exterior alrededor de una vista?**
   * **Respuesta correcta:** `layout_margin`
   * **Explicación:** `layout_margin` establece distancia exterior respecto a los elementos vecinos o los límites del padre.

7. **¿Para qué se utiliza principalmente `ConstraintLayout`?**
   * **Respuesta correcta:** Para definir restricciones
   * **Explicación:** `ConstraintLayout` posiciona y dimensiona vistas mediante relaciones (restricciones) con otras vistas o con el contenedor padre.

8. **En `ConstraintLayout`, ¿qué significa una anchura de `0dp` con restricciones a ambos lados?**
   * **Respuesta correcta:** Ajustar a restricciones
   * **Explicación:** `0dp` activa el comportamiento *match constraints*, haciendo que la vista ocupe exactamente el espacio entre las restricciones izquierda y derecha.

9. **¿Qué atributo centra los elementos hijos dentro de un `LinearLayout`?**
   * **Respuesta correcta:** `gravity`
   * **Explicación:** `android:gravity` controla la alineación o colocación del contenido interno dentro del contenedor.

10. **¿Qué unidad se recomienda para definir el tamaño del texto en Android?**
    * **Respuesta correcta:** `sp`
    * **Explicación:** `sp` (*scale-independent pixels*) escala el tamaño del texto teniendo en cuenta las preferencias de tamaño de fuente del usuario.

11. **¿Qué atributo conecta la parte superior de una vista con la inferior de otra?**
    * **Respuesta correcta:** `layout_constraintTop_toBottomOf`
    * **Explicación:** Esta restricción sitúa el borde superior (`Top`) de la vista respecto al borde inferior (`BottomOf`) de otra vista de referencia.

12. **¿Qué atributo permite asignar un identificador nuevo a una vista en XML?**
    * **Respuesta correcta:** `android:id="@+id/..."`
    * **Explicación:** La sintaxis `@+id/` declara un nuevo identificador en la clase `R.id` para que luego pueda referenciarse desde XML o Kotlin.

13. **En Kotlin, ¿qué palabra clave declara una referencia que no puede reasignarse?**
    * **Respuesta correcta:** `val`
    * **Explicación:** `val` define una referencia de solo lectura que impide reasignar otro valor tras su inicialización.

14. **¿Qué palabra clave permite reasignar una variable en Kotlin?**
    * **Respuesta correcta:** `var`
    * **Explicación:** `var` declara una variable mutable a la que se le pueden asignar nuevos valores durante la ejecución.

15. **¿Qué tipo de Kotlin representa un valor verdadero o falso?**
    * **Respuesta correcta:** `Boolean`
    * **Explicación:** El tipo `Boolean` admite únicamente los valores lógicos `true` y `false`.

16. **¿Qué método convierte habitualmente un valor a su representación textual?**
    * **Respuesta correcta:** `toString()`
    * **Explicación:** `toString()` obtiene la representación en cadena de texto (`String`) de cualquier valor u objeto.

17. **¿Qué operación elimina espacios al inicio y al final de un texto?**
    * **Respuesta correcta:** `trim()`
    * **Explicación:** `trim()` devuelve una copia del texto eliminando los espacios y caracteres en blanco en ambos extremos.

18. **¿Qué operador lógico representa la condición «O» en Kotlin?**
    * **Respuesta correcta:** `||`
    * **Explicación:** El operador lógico OR (`||`) devuelve `true` cuando al menos una de las dos condiciones evaluadas es verdadera.

19. **¿Qué estructura permite ejecutar instrucciones solo cuando se cumple una condición?**
    * **Respuesta correcta:** `if`
    * **Explicación:** La sentencia condicional `if` evalúa una expresión booleana y ejecuta su bloque solo si el resultado es `true`.

20. **¿Qué indica el símbolo `?` después de un tipo en Kotlin?**
    * **Respuesta correcta:** Que admite `null`
    * **Explicación:** El sufijo `?` (por ejemplo, `String?`) indica que una variable de ese tipo puede almacenar una referencia nula (`null`).

21. **¿Qué palabra clave se usa para declarar una función en Kotlin?**
    * **Respuesta correcta:** `fun`
    * **Explicación:** `fun` es la palabra reservada que introduce la declaración de cualquier función en Kotlin.

22. **¿Qué operador comprueba igualdad de valores en Kotlin?**
    * **Respuesta correcta:** `==`
    * **Explicación:** `==` compara la igualdad estructural de valores, mientras que `=` se utiliza para asignar un valor a una variable.

23. **¿Qué método se utiliza para asociar una acción a la pulsación de un botón?**
    * **Respuesta correcta:** `setOnClickListener()`
    * **Explicación:** `setOnClickListener` registra el listener o bloque de código que responde cuando el usuario pulsa la vista.

24. **¿Qué método relaciona una `Activity` con su interfaz XML?**
    * **Respuesta correcta:** `setContentView()`
    * **Explicación:** `setContentView(R.layout....)` infla y establece el diseño XML que mostrará la `Activity` en pantalla.

25. **¿Qué método permite obtener una referencia a una vista mediante su ID?**
    * **Respuesta correcta:** `findViewById()`
    * **Explicación:** `findViewById(R.id....)` busca y devuelve una referencia a la vista correspondiente dentro del árbol de interfaz.

26. **¿Qué objeto se utiliza habitualmente para solicitar la apertura de otra `Activity`?**
    * **Respuesta correcta:** `Intent`
    * **Explicación:** Un `Intent` explícito identifica la clase de la `Activity` destino que se desea abrir.

27. **¿Qué método inicia una `Activity` a partir de un `Intent`?**
    * **Respuesta correcta:** `startActivity()`
    * **Explicación:** `startActivity(intent)` envía la petición al sistema Android para iniciar la `Activity` indicada en el `Intent`.

28. **¿Qué método añade un dato identificado por una clave a un `Intent`?**
    * **Respuesta correcta:** `putExtra()`
    * **Explicación:** `putExtra(clave, valor)` incorpora datos adicionales en forma de pares clave-valor dentro del `Intent`.

29. **¿Qué método del ciclo de vida se ejecuta al crear una `Activity`?**
    * **Respuesta correcta:** `onCreate()`
    * **Explicación:** `onCreate()` es el primer método del ciclo de vida de una `Activity` y el lugar habitual para inicializar la interfaz y variables.

30. **¿Qué componente permite mostrar un aviso breve y temporal en pantalla?**
    * **Respuesta correcta:** `Toast`
    * **Explicación:** `Toast` muestra un mensaje flotante temporal en la parte inferior de la pantalla sin bloquear ni sustituir la vista actual.