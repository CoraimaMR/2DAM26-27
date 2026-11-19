# Guía Completa UD2: Acceso a Datos con JDBC y PostgreSQL (Proyecto Tienda)

Este documento reúne la teoría esencial basada en el proyecto Java (`ConexionBD`, `ClienteDAO`, `Cliente`, `VentanaPrincipal`) y los scripts SQL (`00_crear_base_datos.sql`, `01_crear_esquema.sql`), junto con el cuestionario resuelto y comentado para repaso.

---

# PARTE I: APUNTES Y ESQUEMAS DEL PROYECTO

## 1. Arquitectura General del Proyecto

El proyecto separa responsabilidades siguiendo el patrón **DAO (Data Access Object)** para aislar la lógica de acceso a datos de la interfaz gráfica construida con Java Swing.

```text
+--------------------+      +---------------+      +--------------+      +---------------+
|  VentanaPrincipal  | ---> |  ClienteDAO   | ---> |  ConexionBD  | ---> |  PostgreSQL   |
|  (Interfaz Swing)  |      |  (SQL / CRUD) |      |   (JDBC)     |      | (tienda_ud2)  |
+--------------------+      +---------------+      +--------------+      +---------------+
         |                          |
         v                          v
  JTable / Botones           Mapeo Fila <-> Objeto Cliente
```

* **`VentanaPrincipal` (Capa de Vista):** Dibuja ventanas, botones, tablas (`JTable`) y gestiona los eventos del usuario (clics, selección de filas). **Nunca** ejecuta SQL directamente.
* **`ClienteDAO` (Capa de Persistencia):** Contiene las consultas SQL (`SELECT`, `INSERT`, `UPDATE`, `DELETE`) y mapea las filas de la base de datos a objetos Java `Cliente` (y viceversa).
* **`ConexionBD` (Capa de Conexión):** Centraliza la URL JDBC, el usuario, la contraseña y la creación de objetos `Connection`.
* **`Cliente` / `Producto` (Capa de Modelo):** Clases Java (POJOs) que representan las entidades o registros de las tablas.

---

## 2. Administración y Permisos en PostgreSQL

Para evitar que la aplicación utilice el superusuario `postgres`, se crea un usuario dedicado (`tienda_app`) al que se le conceden privilegios de forma escalonada (de mayor a menor nivel).

### Esquema de Jerarquía de Privilegios

```text
[Servidor PostgreSQL]
 ├── 1. CREATE USER tienda_app WITH PASSWORD '...';   --> Crea el rol con inicio de sesión
 └── 2. CREATE DATABASE tienda_ud2;                  --> Crea la base de datos
      └── 3. GRANT CONNECT ON DATABASE ...           --> Permite conectarse a la BD
           └── [Esquema: public]
                ├── 4. GRANT USAGE ON SCHEMA ...     --> Permite resolver/ver objetos del esquema
                ├── 5. GRANT CREATE ON SCHEMA ...    --> Permite crear tablas/objetos dentro
                └── [Tablas existentes]
                     └── 6. GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES ... (Operaciones DML)
```

### Resumen de Sentencias SQL de Administración

| Sentencia SQL | Función Exacta |
| :--- | :--- |
| `CREATE USER tienda_app WITH PASSWORD '...';` | Define un rol con capacidad de inicio de sesión y establece su contraseña. |
| `CREATE DATABASE tienda_ud2;` | Crea una base de datos nueva en el servidor (requiere privilegios de creación de BD). |
| `GRANT CONNECT ON DATABASE tienda_ud2 TO tienda_app;` | Concede permiso para iniciar conexión con esa base de datos (no da acceso a tablas por sí solo). |
| `GRANT USAGE ON SCHEMA public TO tienda_app;` | Permite acceder y resolver nombres dentro del esquema `public` (los permisos sobre tablas se dan aparte). |
| `GRANT CREATE ON SCHEMA public TO tienda_app;` | Autoriza a crear objetos (como tablas o vistas) dentro del esquema `public`. |
| `GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO tienda_app;` | Concede operaciones de manipulación de datos (DML) sobre las tablas **existentes** en ese momento en el esquema. |

---

## 3. Diseño de Tablas, Tipos y Restricciones (`01_crear_esquema.sql`)

### Diagrama Relacional de la Base de Datos `tienda_ud2`

```text
+-----------------------------------+         +---------------------------------------+
|              cliente              |         |               producto                |
+-----------------------------------+         +---------------------------------------+
| PK  id : INTEGER (IDENTITY)       |         | PK  id : INTEGER (IDENTITY)           |
|     nombre : VARCHAR(100) NN      |         |     nombre : VARCHAR(120) NN          |
| UQ  email : VARCHAR(150) NN       |         |     precio : NUMERIC(10,2) NN (CK>=0) |
|     activo : BOOLEAN NN DEF TRUE  |         |     stock : INTEGER NN DEF 0 (CK>=0)  |
+-----------------------------------+         +---------------------------------------+
                                                                  ^
                                                                  | 1
                                                                  |
                                                                  | N (FK producto_id)
                                              +---------------------------------------+
                                              |           movimiento_stock            |
                                              +---------------------------------------+
                                              | PK  id : BIGINT (IDENTITY)            |
                                              | FK  producto_id : INTEGER NN          |
                                              |     cantidad : INTEGER NN             |
                                              |     fecha : TIMESTAMP DEF NOW()       |
                                              +---------------------------------------+
```

### Glosario de Tipos y Restricciones

* **`GENERATED ALWAYS AS IDENTITY`:** Indica que PostgreSQL genera el identificador numérico de forma automática y secuencial al insertar filas, restringiendo la inserción manual de valores explícitos.
* **`PRIMARY KEY (id)`:** Clave primaria que identifica de forma única cada registro de la tabla; implica **unicidad** (`UNIQUE`) y **no nulidad** (`NOT NULL`).
* **`UNIQUE (email)`:** Restricción que impide que existan dos filas con el mismo correo electrónico no nulo.
* **`CHECK (precio >= 0)`:** Comprueba una condición lógica en cada `INSERT` o `UPDATE`; rechaza cualquier registro con precio negativo.
* **`DEFAULT TRUE`:** Asigna automáticamente el valor `TRUE` a la columna `activo` cuando se omite en una sentencia `INSERT`.
* **`NUMERIC(10,2)`:** Tipo decimal exacto con **precisión 10** (hasta 10 dígitos significativos en total) y **escala 2** (2 cifras tras la coma decimal).
* **`FOREIGN KEY (producto_id) REFERENCES producto(id)`:** Clave foránea que garantiza la **integridad referencial**, exigiendo que el `producto_id` exista previamente en la tabla `producto`.

---

## 4. Conexión JDBC (`ConexionBD.java`)

JDBC (*Java Database Connectivity*) permite conectar aplicaciones Java con bases de datos relacionales mediante un controlador (driver) específico configurado en `pom.xml`.

### Estructura de la URL de Conexión JDBC

```text
jdbc:postgresql://localhost:5432/tienda_ud2
└─┬─┘ └───┬────┘  └───┬───┘ └─┬─┘ └───┬────┘
  │       │           │       │       └── Nombre de la base de datos
  │       │           │       └────────── Puerto de escucha de PostgreSQL
  │       │           └────────────────── Dirección del servidor (host)
  │       └────────────────────────────── Subprotocolo (gestor de BD)
  └────────────────────────────────────── Protocolo principal JDBC
```

### Elementos Clave de Conexión

* **`DriverManager.getConnection(url, usuario, password)`:** Método estático que solicita al driver adecuado abrir una conexión con la base de datos y devuelve un objeto `Connection`.
* **`SQLException`:** Excepción comprobada (*checked exception*) estándar de JDBC que se declara o captura en métodos que pueden fallar por errores de red, SQL o permisos.

---

## 5. Operaciones CRUD con `PreparedStatement` y `ResultSet` (`ClienteDAO.java`)

### Flujo de Ejecución JDBC

```text
                   ┌──> executeQuery()  ──> ResultSet (filas) ──> while(rs.next()) ──> Objetos Java
                   │    (Para SELECT)
Connection ──> PreparedStatement
                   │
                   └──> executeUpdate() ──> int (filas afectadas) + getGeneratedKeys() (IDs)
                        (Para INSERT, UPDATE, DELETE)
```

### Reglas de Uso de `PreparedStatement`

1. **Parámetros `?`:** Representan marcadores de posición que evitan ataques de inyección SQL y gestionan tipos de datos automáticamente.
2. **Numeración desde `1`:** A diferencia de los arrays de Java (índice `0`), los parámetros `?` **se numeran empezando en `1`**.
   * Ejemplo: `ps.setBoolean(3, valor)` asigna el valor booleano al **tercer** parámetro `?`.

### Operaciones CRUD Detalladas

#### 1. Consultar (`SELECT`) — `listar()`
* Se ejecuta mediante **`ps.executeQuery()`**, obteniendo un objeto **`ResultSet`**.
* El cursor del `ResultSet` se sitúa inicialmente **antes de la primera fila**. Es obligatorio llamar a **`rs.next()`** antes de leer datos para avanzar el cursor y verificar si existe una fila.
* Lectura de campos: `rs.getInt("id")`, `rs.getString("nombre")`, `rs.getBoolean("activo")`.

#### 2. Insertar (`INSERT`) y Recuperar Clave Generada — `insertar(Cliente c)`
* Sentencia SQL: `INSERT INTO cliente(nombre, email, activo) VALUES (?, ?, ?)`.
* Se prepara con **`Statement.RETURN_GENERATED_KEYS`** para indicar al driver que recupere el `id` autogenerado por PostgreSQL.
* Se ejecuta con **`ps.executeUpdate()`**.
* Se obtiene el `ResultSet` de claves mediante `ps.getGeneratedKeys()` y se asigna al objeto Java:
  ```java
  try (ResultSet claves = ps.getGeneratedKeys()) {
      if (claves.next()) {
          cliente.setId(claves.getInt(1)); // Guarda en el objeto el ID generado
      }
  }
  ```

#### 3. Actualizar (`UPDATE`) — `actualizar(Cliente c)`
* Sentencia SQL: `UPDATE cliente SET nombre = ?, email = ?, activo = ? WHERE id = ?`.
* **Cláusula `WHERE id = ?`:** Es esencial para modificar **únicamente** el cliente seleccionado; sin `WHERE`, se modificarían todas las filas de la tabla.
* **`ps.executeUpdate()`** devuelve un entero (`int`) con el **número de filas afectadas**.
* La expresión `return ps.executeUpdate() == 1;` devuelve `true` si se actualizó exactamente una fila.

#### 4. Eliminar (`DELETE`) — `eliminar(int id)`
* Sentencia SQL: `DELETE FROM cliente WHERE id = ?`.
* Al igual que en la actualización, `ps.executeUpdate() == 1` confirma que se borró exactamente un registro.

---

# PARTE II: CUESTIONARIO RESUELTO Y COMENTADO (30 PREGUNTAS)

1. **¿Qué sentencia crea un usuario con contraseña en PostgreSQL?**
   * **Respuesta correcta:** `CREATE USER tienda_app WITH PASSWORD ...`
   * **Explicación:** `CREATE USER` define un rol con capacidad de inicio de sesión; `PASSWORD` establece su contraseña.

2. **¿Qué sentencia crea la base de datos `tienda_ud2`?**
   * **Respuesta correcta:** `CREATE DATABASE tienda_ud2;`
   * **Explicación:** `CREATE DATABASE` crea una base de datos nueva; requiere los privilegios correspondientes.

3. **¿Qué concede `GRANT CONNECT ON DATABASE tienda_ud2 TO tienda_app`?**
   * **Respuesta correcta:** Permiso para conectarse a esa base.
   * **Explicación:** `CONNECT` permite iniciar conexión a la base, sin conceder por sí solo acceso a las tablas.

4. **¿Para qué sirve `GRANT USAGE ON SCHEMA public TO tienda_app`?**
   * **Respuesta correcta:** Permite utilizar objetos del esquema según otros permisos.
   * **Explicación:** `USAGE` permite resolver nombres del esquema; los permisos sobre tablas se conceden aparte.

5. **¿Qué permite `GRANT CREATE ON SCHEMA public TO tienda_app`?**
   * **Respuesta correcta:** Crear objetos dentro del esquema `public`.
   * **Explicación:** `CREATE` sobre un esquema autoriza la creación de objetos (tablas, vistas, etc.) dentro de él.

6. **¿Qué efecto tiene `GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public`?**
   * **Respuesta correcta:** Concede operaciones DML sobre tablas existentes.
   * **Explicación:** La sentencia concede esos privilegios sobre las tablas existentes del esquema en ese momento.

7. **¿Qué significa `GENERATED ALWAYS AS IDENTITY` en la columna `id`?**
   * **Respuesta correcta:** Que PostgreSQL genera su valor automáticamente.
   * **Explicación:** `IDENTITY` permite generar identificadores; `GENERATED ALWAYS` restringe valores explícitos salvo excepciones.

8. **¿Qué restricción impide repetir el email en la tabla `cliente`?**
   * **Respuesta correcta:** `UNIQUE (email)`
   * **Explicación:** `UNIQUE` exige que los valores no nulos de la columna sean únicos en toda la tabla.

9. **¿Qué restricción identifica de forma única una fila de `cliente`?**
   * **Respuesta correcta:** `PRIMARY KEY (id)`
   * **Explicación:** La clave primaria identifica registros de forma inequívoca y exige unicidad y no nulidad.

10. **¿Qué consigue `CHECK (precio >= 0)` en `producto`?**
    * **Respuesta correcta:** Rechazar precios negativos.
    * **Explicación:** `CHECK` verifica la condición lógica en cada fila insertada o actualizada.

11. **¿Qué representa `NUMERIC(10,2)` para `precio` en PostgreSQL?**
    * **Respuesta correcta:** Un decimal con precisión 10 y escala 2.
    * **Explicación:** `NUMERIC(10,2)` permite hasta 10 dígitos significativos en total, dos de ellos tras la coma decimal.

12. **¿Qué efecto tiene `DEFAULT TRUE` sobre `activo`?**
    * **Respuesta correcta:** Asigna `true` cuando se omite esa columna.
    * **Explicación:** `DEFAULT TRUE` se aplica automáticamente cuando el `INSERT` no especifica el valor de `activo`.

13. **¿Qué hace una `FOREIGN KEY producto_id REFERENCES producto(id)`?**
    * **Respuesta correcta:** Exige referencias válidas a productos.
    * **Explicación:** La clave foránea garantiza integridad referencial entre `movimiento_stock` y `producto`.

14. **¿Qué sentencia añade una fila nueva a `cliente`?**
    * **Respuesta correcta:** `INSERT INTO cliente(nombre,email,activo) VALUES (?,?,?)`
    * **Explicación:** `INSERT INTO ... VALUES` inserta nuevos registros; los `?` son marcadores de parámetros para `PreparedStatement`.

15. **¿Qué método de JDBC se utiliza para establecer una conexión con una base de datos mediante una URL, un usuario y una contraseña?**
    * **Respuesta correcta:** `DriverManager.getConnection()`
    * **Explicación:** Establece una conexión JDBC utilizando la URL y credenciales, devolviendo un objeto `Connection`.

16. **¿Qué representa `jdbc:postgresql://localhost:5432/tienda_ud2`?**
    * **Respuesta correcta:** La URL JDBC de conexión a PostgreSQL.
    * **Explicación:** La cadena incluye protocolo JDBC, gestor, servidor, puerto y nombre de la base de datos.

17. **¿Qué método de `DriverManager` establece una conexión JDBC?**
    * **Respuesta correcta:** `getConnection()`
    * **Explicación:** `DriverManager.getConnection()` solicita y devuelve una conexión mediante el controlador adecuado.

18. **¿Qué excepción se declara normalmente en métodos JDBC que pueden fallar?**
    * **Respuesta correcta:** `SQLException`
    * **Explicación:** `SQLException` es la excepción comprobada habitual de acceso a datos en JDBC.

19. **¿Qué objeto JDBC prepara una sentencia SQL con parámetros `?`?**
    * **Respuesta correcta:** `PreparedStatement`
    * **Explicación:** `PreparedStatement` permite asignar parámetros tipados de forma segura antes de ejecutar el SQL.

20. **¿En qué posición comienza la numeración de parámetros de `PreparedStatement`?**
    * **Respuesta correcta:** `1`
    * **Explicación:** Los parámetros `?` se numeran desde `1`, a diferencia de los índices de arrays en Java que empiezan en `0`.

21. **¿Qué llamada asigna un booleano al tercer parámetro?**
    * **Respuesta correcta:** `ps.setBoolean(3, valor)`
    * **Explicación:** `setBoolean(indice, valor)` enlaza un parámetro de tipo `boolean` en la posición indicada (`3`).

22. **¿Qué método se usa para ejecutar un `SELECT` y obtener filas?**
    * **Respuesta correcta:** `executeQuery()`
    * **Explicación:** `executeQuery()` ejecuta consultas de lectura y devuelve un `ResultSet` con los registros.

23. **¿Qué debe hacerse antes de leer la primera fila de un `ResultSet`?**
    * **Respuesta correcta:** Llamar a `rs.next()`
    * **Explicación:** El cursor empieza antes de la primera fila; `next()` avanza a la siguiente fila y devuelve `true` si existe.

24. **¿Qué llamada recupera la columna `activo` como `boolean`?**
    * **Respuesta correcta:** `rs.getBoolean("activo")`
    * **Explicación:** `ResultSet.getBoolean` lee el valor booleano de la columna indicada en la fila actual.

25. **¿Qué devuelve `executeUpdate()` tras un `UPDATE` habitual?**
    * **Respuesta correcta:** El número de filas afectadas.
    * **Explicación:** `executeUpdate()` devuelve un `int` indicando cuántas filas fueron modificadas, insertadas o borradas.

26. **¿Para qué se usa `Statement.RETURN_GENERATED_KEYS` al insertar?**
    * **Respuesta correcta:** Para solicitar los identificadores generados.
    * **Explicación:** Indica al driver que retenga las claves autogeneradas para recuperarlas después con `getGeneratedKeys()`.

27. **¿Qué hace `cliente.setId(claves.getInt(1))` después de insertar?**
    * **Respuesta correcta:** Guarda en el objeto el ID generado.
    * **Explicación:** Lee la primera columna del `ResultSet` de claves generadas y la asigna al atributo `id` del objeto `Cliente`.

28. **¿Por qué es esencial `WHERE id = ?` en `actualizar()`?**
    * **Respuesta correcta:** Para modificar solo el cliente elegido.
    * **Explicación:** Sin cláusula `WHERE`, un `UPDATE` sobrescribiría todas las filas de la tabla `cliente`.

29. **¿Qué significa `return ps.executeUpdate() == 1` en `actualizar()`?**
    * **Respuesta correcta:** Devuelve `true` si afectó exactamente una fila.
    * **Explicación:** Compara el número de filas modificadas con `1` y devuelve el resultado booleano de esa comprobación.

30. **¿Qué responsabilidad corresponde a `ClienteDAO` en el proyecto Swing?**
    * **Respuesta correcta:** Ejecutar SQL y mapear filas a `Cliente`.
    * **Explicación:** La clase DAO aísla toda la lógica de persistencia, mientras que `VentanaPrincipal` se ocupa de la interfaz gráfica.