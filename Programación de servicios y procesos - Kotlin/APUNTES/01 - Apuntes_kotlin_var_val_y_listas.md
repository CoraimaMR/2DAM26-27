# 📚 Apuntes de Kotlin: `var`, `val` y Colecciones (`listOf` vs `mutableListOf`)

---

## 1. Conceptos Fundamentales: `var` vs `val`

En Kotlin, la gestión de la memoria y la mutabilidad de las variables se controla desde el momento en que se declaran. Existen dos palabras clave principales: **`var`** y **`val`**.

### 🔹 `var` (Variable / Mutable)
* **Definición:** Es una variable tradicional cuyo valor **puede cambiar** (reasignarse) a lo largo de la ejecución del programa.
* **Analogía:** Una **pizarra blanca**. Puedes escribir un dato, borrarlo y escribir uno nuevo tantas veces como quieras.
* **Uso recomendado:** Úsala solo cuando sepas con certeza que el valor de esa variable va a necesitar actualizarse (ej. un contador, la puntuación de un jugador, el estado de carga).

```kotlin
var puntos = 10
puntos = 20 // ✅ PERMITIDO: Estamos cambiando el valor de la variable.
puntos = 35 // ✅ PERMITIDO
```

### 🔹 `val` (Value / Inmutable o de solo lectura)
* **Definición:** Es una referencia de **solo lectura**. Una vez que se le asigna un valor inicial, **no puede volver a apuntar a otro valor** diferente. *(Nota: Es similar al `final` de Java o `const` en JavaScript).*
* **Analogía:** Un **tatuaje** o algo escrito con **bolígrafo permanente**. Una vez puesto, no se puede borrar para poner otra cosa en su lugar.
* **Uso recomendado:** **Por defecto, usa siempre `val`**. Hace que tu código sea más seguro, predecible y libre de errores accidentales.

```kotlin
val nombre = "Ana"
nombre = "Laura" // ❌ ERROR DE COMPILACIÓN: Val cannot be reassigned.
```

### 📊 Tabla Comparativa Rápida

| Característica | `var` | `val` |
| :--- | :--- | :--- |
| **¿Se puede reasignar?** | ✅ Sí | ❌ No |
| **Mutabilidad** | Mutable | Solo lectura (Inmutable la referencia) |
| **Seguridad en el código** | Menor (puede cambiar por error) | Mayor (garantiza estabilidad) |
| **Buena práctica** | Usar solo si es necesario | **Usar siempre por defecto** |

---

## 2. Colecciones: `listOf` vs `mutableListOf`

Al trabajar con listas en Kotlin, también debemos diferenciar entre listas que no se pueden modificar y listas que sí permiten añadir, borrar o actualizar elementos.

### 🔸 `listOf()` (Lista de solo lectura / Inmutable)
* Crea una lista **fija**.
* Una vez creada, **no** puedes añadir (`add`), eliminar (`remove`) ni modificar (`set`) sus elementos.
* Solo permite operaciones de lectura (obtener tamaño, leer una posición, recorrerla).

```kotlin
val frutas = listOf("Manzana", "Plátano", "Naranja")
println(frutas[0]) // ✅ Imprime: Manzana
// frutas.add("Pera") // ❌ ERROR: No existe el método .add() en esta lista
```

### 🔸 `mutableListOf()` (Lista Mutable)
* Crea una lista **dinámica**.
* Permite modificar su contenido interno: añadir elementos, eliminarlos, ordenarlos o cambiar el valor de una posición específica.

```kotlin
val colores = mutableListOf("Rojo", "Verde")
colores.add("Azul")      // ✅ Añade "Azul" -> ["Rojo", "Verde", "Azul"]
colores.remove("Rojo")   // ✅ Elimina "Rojo" -> ["Verde", "Azul"]
colores[0] = "Amarillo"  // ✅ Modifica posición 0 -> ["Amarillo", "Azul"]
```

---

## 3. 🎯 El Punto Clave: La unión de `var`/`val` con las Listas

Aquí es donde suele haber más confusión al aprender Kotlin. Es vital entender la diferencia entre **la caja (la variable)** y **el contenido de la caja (la lista)**.

* **`val` o `var`** controlan **la caja** (si puedes cambiar la lista entera por otra nueva).
* **`listOf` o `mutableListOf`** controlan **el contenido** (si puedes meter o sacar cosas de esa lista).

Existen **4 combinaciones posibles**:

---

### Combinación 1: `val` + `listOf` (La más estricta y segura 🔒)
* **La caja (`val`):** No se puede cambiar por otra caja.
* **El contenido (`listOf`):** No se puede modificar.
* **Resultado:** Inmutabilidad total. Ni la lista ni sus elementos cambiarán jamás.

```kotlin
val diasSemana = listOf("Lunes", "Martes", "Miércoles")

// diasSemana.add("Jueves")          // ❌ ERROR: El contenido es inmutable.
// diasSemana = listOf("Sábado")     // ❌ ERROR: La variable 'val' no se puede reasignar.
```

---

### Combinación 2: `val` + `mutableListOf` (⭐ LA MÁS USADA EN LA PRÁCTICA)
* **La caja (`val`):** Siempre será la misma lista (no puedes asignarle una lista nueva con `=`).
* **El contenido (`mutableListOf`):** Puedes añadir, quitar o editar elementos dentro de ella.
* **¿Por qué se usa tanto?** Porque normalmente creas una lista (por ejemplo, un carrito de la compra), vas metiendo y sacando productos, pero **el carrito sigue siendo el mismo**.

```kotlin
val carritoCompra = mutableListOf("Pan", "Leche")

// Modificar el CONTENIDO (✅ PERMITIDO)
carritoCompra.add("Huevos")      // Ahora tiene: ["Pan", "Leche", "Huevos"]
carritoCompra.remove("Pan")      // Ahora tiene: ["Leche", "Huevos"]

// Cambiar la REFERENCIA/CAJA (❌ ERROR)
// carritoCompra = mutableListOf("Carne") // ❌ ERROR: Val cannot be reassigned.
```
> 💡 **Regla de oro:** Que una variable sea `val` **NO** significa que los datos de dentro no puedan cambiar, significa que **la referencia en memoria** siempre apunta al mismo objeto.

---

### Combinación 3: `var` + `listOf` (Cambio total de lista 🔄)
* **La caja (`var`):** Puedes tirar la lista actual y asignar una lista completamente nueva.
* **El contenido (`listOf`):** No puedes modificar elementos sueltos de la lista actual; para cambiar algo, debes reemplazar la lista entera.
* **Uso común:** Muy habitual en arquitecturas modernas (como Jetpack Compose / MVVM) donde el estado de la pantalla se actualiza reemplazando listas completas por seguridad.

```kotlin
var alumnos = listOf("Carlos", "Elena")

// Modificar el CONTENIDO directamente (❌ ERROR)
// alumnos.add("Marcos") // ❌ ERROR: Es un listOf, no tiene .add()

// Reemplazar la LISTA ENTERA (✅ PERMITIDO gracias a 'var')
alumnos = listOf("Carlos", "Elena", "Marcos") // Creamos una lista nueva y la asignamos
```

---

### Combinación 4: `var` + `mutableListOf` (Flexibilidad total ⚠️)
* **La caja (`var`):** Puedes reasignar una lista nueva.
* **El contenido (`mutableListOf`):** Puedes modificar los elementos internos.
* **Uso común:** Poco recomendada salvo casos muy específicos, ya que tener doble mutabilidad puede hacer difícil rastrear dónde y cómo se modificaron los datos en programas grandes.

```kotlin
var tareas = mutableListOf("Estudiar")

tareas.add("Hacer ejercicio")           // ✅ Modifica el contenido
tareas = mutableListOf("Descansar")     // ✅ Reemplaza la lista entera
```

---

## 4. Resumen Visual (Chuleta definitiva)

```text
               ┌─────────────────────────────────────────────────────────┐
               │                  CONTENIDO DE LA LISTA                  │
               ├────────────────────────────┬────────────────────────────┤
               │         listOf()           │      mutableListOf()       │
               │   (No añade/borra items)   │    (Sí añade/borra items)  │
┌──────────────┼────────────────────────────┼────────────────────────────┤
│     val      │  ❌ Reasignar lista        │  ❌ Reasignar lista        │
│ (Misma caja) │  ❌ Modificar items        │  ✅ Modificar items (⭐)   │
├──────────────┼────────────────────────────┼────────────────────────────┤
│     var      │  ✅ Reasignar lista        │  ✅ Reasignar lista        │
│ (Otra caja)  │  ❌ Modificar items        │  ✅ Modificar items        │
└──────────────┴────────────────────────────┴────────────────────────────┘
```

### 🧠 Regla nemotécnica para recordar:
1. **`val` vs `var`**: Pregúntate *"¿Voy a usar el símbolo `=` otra vez con esta variable?"*. Si es **NO** $\rightarrow$ `val`. Si es **SÍ** $\rightarrow$ `var`.
2. **`listOf` vs `mutableListOf`**: Pregúntate *"¿Voy a usar `.add()` o `.remove()` en esta lista?"*. Si es **NO** $\rightarrow$ `listOf`. Si es **SÍ** $\rightarrow$ `mutableListOf`.