# Apuntes de Acceso a Datos: PostgreSQL y JDBC en Java

Estos apuntes resumen los conceptos clave sobre administración básica de permisos en **PostgreSQL**, diseño de tablas relacionales (DDL), consultas SQL (DML) y conexión desde Java mediante **JDBC** y el patrón **DAO**.

\---

## 1\. Administración de Usuarios, Bases de Datos y Permisos en PostgreSQL

### Creación de Roles y Bases de Datos

* **Crear un usuario con contraseña:**

```sql
  CREATE USER tienda\_app WITH PASSWORD 'contraseña';
  ```

* **Crear una base de datos:**

```sql
  CREATE DATABASE tienda\_ud2;
  ```

### Concesión de Privilegios (`GRANT`)

En PostgreSQL los permisos se otorgan de forma granular por niveles (base de datos, esquema y objetos):

* **Conexión a la base de datos:**
`GRANT CONNECT ON DATABASE tienda\_ud2 TO tienda\_app;`
Concede permiso para conectarse a esa base de datos específica.
* **Uso del esquema:**
`GRANT USAGE ON SCHEMA public TO tienda\_app;`
Permite acceder y utilizar los objetos dentro del esquema `public` siempre que el usuario tenga también permisos sobre esos objetos individuales.
* **Creación en el esquema:**
`GRANT CREATE ON SCHEMA public TO tienda\_app;`
Permite crear nuevos objetos (tablas, vistas, secuencias, etc.) dentro del esquema `public`.
* **Operaciones sobre tablas existentes:**
`GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO tienda\_app;`
Concede permisos de manipulación de datos (DML) sobre todas las tablas que ya existen en ese esquema.

\---

## 2\. Diseño de Tablas y Restricciones (DDL) en PostgreSQL

### Tipos de Datos y Generación Automática

* **Claves autoincrementales (`GENERATED ALWAYS AS IDENTITY`):** Indica que PostgreSQL genera el valor de la columna `id` de forma automática y secuencial al insertar un registro.
* **Decimales exactos (`NUMERIC(10,2)`):** Representa un número decimal exacto con una **precisión de 10 dígitos en total** y una **escala de 2 decimales** (ideal para precios o importes monetarios).
* **Valores por defecto (`DEFAULT TRUE`):** Asigna automáticamente el valor `true` a la columna (ej. `activo`) cuando se omite dicha columna en una sentencia `INSERT`.

### Restricciones de Integridad (*Constraints*)

* **`PRIMARY KEY (id)`:** Identifica de forma única cada fila de la tabla (implica unicidad y no nulidad).
* **`UNIQUE (email)`:** Impide que se repita el mismo valor de correo electrónico entre distintos registros de la tabla `cliente`.
* **`CHECK (precio >= 0)`:** Valida una condición lógica antes de guardar el dato, rechazando precios negativos.
* **`FOREIGN KEY producto\_id REFERENCES producto(id)`:** Garantiza la integridad referencial exigiendo que todo valor en `producto\_id` corresponda a un `id` válido existente en la tabla `producto`.

### Sentencias DML Básicas

* **Inserción parametrizada:**

```sql
  INSERT INTO cliente(nombre, email, activo) VALUES (?, ?, ?);
  ```

\---

## 3\. Conexión y Ejecución SQL con JDBC

### Configuración y Conexión

* **URL de conexión JDBC:** Tiene el formato `jdbc:postgresql://localhost:5432/tienda\_ud2`, indicando el protocolo (`jdbc:postgresql`), el servidor (`localhost`), el puerto (`5432`) y el nombre de la base de datos (`tienda\_ud2`).
* **Establecer conexión (`DriverManager.getConnection()`):** Método estático utilizado para abrir una sesión con la base de datos pasando la URL JDBC, el usuario y la contraseña.
* **Gestión de errores (`SQLException`):** Es la excepción comprobada (*checked exception*) que se declara y captura habitualmente en los métodos JDBC ante fallos de red, sintaxis SQL o restricciones de integridad.

### Consultas Parametrizadas (`PreparedStatement`)

* **Precompilación y seguridad:** `PreparedStatement` prepara sentencias SQL con marcadores de posición (`?`), evitando la inyección SQL y mejorando el rendimiento.
* **Indexación base 1:** A diferencia de los arrays en Java, la numeración de los parámetros en `PreparedStatement` **comienza en 1**.

  * *Ejemplo:* `ps.setBoolean(3, valor)` asigna un valor booleano al tercer parámetro (`?`).

### Ejecución y Lectura de Resultados

* **Consultas de lectura (`executeQuery()`):** Ejecuta una sentencia `SELECT` y devuelve un objeto `ResultSet` con las filas resultantes.
* **Recorrido del `ResultSet`:**

  * Antes de leer la primera fila, es obligatorio llamar a `rs.next()` para avanzar el cursor desde antes de la primera fila hacia el primer registro válido (devuelve `false` si no hay más filas).
  * Para extraer un dato tipado por nombre de columna se usan los métodos *getter* correspondientes, como `rs.getBoolean("activo")`.
* **Consultas de modificación (`executeUpdate()`):** Ejecuta sentencias `INSERT`, `UPDATE` o `DELETE` y devuelve un entero (`int`) con el **número de filas afectadas**.

### Recuperación de Claves Generadas

* Al realizar un `INSERT` en tablas con columnas `IDENTITY`, se pasa la constante `Statement.RETURN\_GENERATED\_KEYS` al preparar la sentencia para solicitar a PostgreSQL los IDs generados.
* Tras ejecutar la inserción, se recuperan mediante `getGeneratedKeys()` y se asignan al objeto Java:

```java
  cliente.setId(claves.getInt(1));
  ```

\---

## 4\. Patrón DAO y Buenas Prácticas en la Aplicación

* **Responsabilidad de `ClienteDAO`:** En una arquitectura por capas (como una aplicación de escritorio con Swing), el DAO (*Data Access Object*) encapsula exclusivamente el acceso a datos: **ejecutar las sentencias SQL y mapear las filas del `ResultSet` a objetos `Cliente`** (separando la persistencia de la interfaz gráfica).
* **Seguridad en actualizaciones (`WHERE id = ?`):** En el método `actualizar()`, incluir la cláusula `WHERE id = ?` es esencial para **modificar únicamente el cliente elegido** y evitar sobrescribir toda la tabla.
* **Comprobación de éxito:** La expresión `return ps.executeUpdate() == 1;` devuelve `true` únicamente si la operación afectó exactamente a una fila en la base de datos.

\---

## Tabla Resumen de Respuestas del Cuestionario

|Pregunta|Concepto / Pregunta|Respuesta Correcta|
|-|-|-|
|**1**|Crear usuario con contraseña en PostgreSQL|`CREATE USER tienda\_app WITH PASSWORD ...`|
|**2**|Crear base de datos `tienda\_ud2`|`CREATE DATABASE tienda\_ud2;`|
|**3**|Qué concede `GRANT CONNECT ON DATABASE...`|Permiso para conectarse a esa base|
|**4**|Para qué sirve `GRANT USAGE ON SCHEMA...`|Permite utilizar objetos del esquema según otros permisos|
|**5**|Qué permite `GRANT CREATE ON SCHEMA...`|Crear objetos dentro del esquema `public`|
|**6**|Efecto de `GRANT SELECT, INSERT, UPDATE, DELETE...`|Concede operaciones DML sobre tablas existentes|
|**7**|Significado de `GENERATED ALWAYS AS IDENTITY`|Que PostgreSQL genera su valor automáticamente|
|**8**|Restricción que impide repetir `email`|`UNIQUE (email)`|
|**9**|Restricción que identifica de forma única una fila|`PRIMARY KEY (id)`|
|**10**|Qué consigue `CHECK (precio >= 0)`|Rechazar precios negativos|
|**11**|Qué representa `NUMERIC(10,2)`|Un decimal con precisión 10 y escala 2|
|**12**|Efecto de `DEFAULT TRUE` sobre `activo`|Asigna `true` cuando se omite esa columna|
|**13**|Qué hace `FOREIGN KEY producto\_id REFERENCES...`|Exige referencias válidas a productos|
|**14**|Sentencia para añadir una fila nueva a `cliente`|`INSERT INTO cliente(nombre,email,activo) VALUES (?,?,?)`|
|**15**|Método JDBC para conectar con URL, usuario y clave|`DriverManager.getConnection()`|
|**16**|Qué representa `jdbc:postgresql://localhost:5432/tienda\_ud2`|La URL JDBC de conexión a PostgreSQL|
|**17**|Método de `DriverManager` que establece conexión|`getConnection()`|
|**18**|Excepción típica en métodos JDBC|`SQLException`|
|**19**|Objeto JDBC que prepara SQL con parámetros `?`|`PreparedStatement`|
|**20**|Posición inicial de parámetros en `PreparedStatement`|`1`|
|**21**|Asignar un booleano al tercer parámetro|`ps.setBoolean(3, valor)`|
|**22**|Método para ejecutar un `SELECT`|`executeQuery()`|
|**23**|Acción previa a leer la primera fila de `ResultSet`|Llamar a `rs.next()`|
|**24**|Recuperar la columna `activo` como `boolean`|`rs.getBoolean("activo")`|
|**25**|Retorno de `executeUpdate()` tras un `UPDATE`|El número de filas afectadas|
|**26**|Uso de `Statement.RETURN\_GENERATED\_KEYS`|Para solicitar los identificadores generados|
|**27**|Qué hace `cliente.setId(claves.getInt(1))`|Guarda en el objeto el ID generado|
|**28**|Por qué es esencial `WHERE id = ?` en `actualizar()`|Para modificar solo el cliente elegido|
|**29**|Significado de `return ps.executeUpdate() == 1`|Devuelve `true` si afectó exactamente una fila|
|**30**|Responsabilidad de `ClienteDAO` en Swing|Ejecutar SQL y mapear filas a `Cliente`|



