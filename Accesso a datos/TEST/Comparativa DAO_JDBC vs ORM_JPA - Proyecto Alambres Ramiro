# Guía de Acceso a Datos: Diferencias entre DAO (JDBC) y ORM (JPA/Hibernate)

Este documento explica cómo se conecta una aplicación Java a una base de datos relacional (**PostgreSQL**) comparando dos enfoques de persistencia presentes en el proyecto **`DAM-ACCESO_A_DATOS_ALAMBRES_RAMIRO`**:
1. **Enfoque Clásico DAO + JDBC** (`Main`, `ConexionBD`, `ClienteDAO`, `Cliente`, `VentanaPrincipal`).
2. **Enfoque Moderno ORM con JPA** (`MainORM`, `JpaUtil`, `persistence.xml`, `ClienteDAOOrm`, `ClienteEntidad`, `TransaccionesORM`, `VentanaPrincipalORM`).

---

## 1. Arquitectura Paralela del Proyecto

El proyecto implementa **dos caminos completos y paralelos** que atacan exactamente a la misma base de datos PostgreSQL (`tienda_ud2`) creada por los scripts `sql/00_crear_base_datos.sql` y `sql/01_crear_esquema.sql`.

```text
====================================================================================================
                                  INTERFAZ DE USUARIO (SWING)
====================================================================================================
           [VentanaPrincipal.java]                             [VentanaPrincipalORM.java]
           (Arranca desde Main.java)                           (Arranca desde MainORM.java)
                      │                                                   │
                      ▼                                                   ▼
====================================================================================================
                              CAPA DE ACCESO A DATOS (PERSISTENCIA)
====================================================================================================
             [ClienteDAO.java]                                   [ClienteDAOOrm.java]
        - Escribe SQL a mano (SELECT/INSERT...)             - Usa EntityManager (persist, merge, find)
        - Usa PreparedStatement y ResultSet                 - Usa JPQL (consultas sobre objetos)
        - Mapeo manual fila <-> objeto                      - Transacciones explícitas (TransaccionesORM)
                      │                                                   │
                      ▼                                                   ▼
====================================================================================================
                                    CAPA DE MODELO / ENTIDADES
====================================================================================================
              [Cliente.java]                                     [ClienteEntidad.java]
        - Clase Java simple (POJO)                          - Clase anotada (@Entity, @Table, @Id...)
        - Sin metadatos de base de datos                    - Mapea columnas y claves directamente
                      │                                                   │
                      ▼                                                   ▼
====================================================================================================
                               GESTIÓN DE CONEXIÓN Y CONFIGURACIÓN
====================================================================================================
             [ConexionBD.java]                                     [JpaUtil.java]
        - Usa DriverManager.getConnection()                 - Crea EntityManagerFactory
        - URL, usuario y clave en código                    - Lee META-INF/persistence.xml
                      │                                                   │
                      └─────────────────────────┬─────────────────────────┘
                                                ▼
====================================================================================================
                             BASE DE DATOS RELACIONAL (POSTGRESQL)
====================================================================================================
                              Base de datos: tienda_ud2 (Tabla: cliente)
                         Creada con: 00_crear_base_datos.sql y 01_crear_esquema.sql
```

---

## 2. Base de Datos Compartida (`sql/`)

Ambos sistemas (JDBC y ORM) trabajan contra el mismo esquema relacional en PostgreSQL:

```text
+-------------------------------------------------------+
|                    Tabla: cliente                     |
+-------------------------------------------------------+
| PK  id     : INTEGER GENERATED ALWAYS AS IDENTITY     |
|     nombre : VARCHAR(100) NOT NULL                    |
| UQ  email  : VARCHAR(150) NOT NULL                    |
|     activo : BOOLEAN NOT NULL DEFAULT TRUE            |
+-------------------------------------------------------+
```

* **`00_crear_base_datos.sql`:** Crea el rol/usuario `tienda_app` y la base de datos `tienda_ud2`.
* **`01_crear_esquema.sql`:** Crea las tablas (`cliente`, `producto`, `movimiento_stock`), sus claves primarias (`PRIMARY KEY`), restricciones (`UNIQUE`, `CHECK`, `DEFAULT`) y asigna permisos al usuario de aplicación.

---

## 3. Análisis Detallado: DAO con JDBC vs. DAO con ORM (JPA)

### A. ¿Qué es el enfoque DAO con JDBC puro?
En este enfoque, el programador tiene el **control total y manual** sobre la comunicación con la base de datos:
1. **Conexión (`ConexionBD.java`):** Se solicita una conexión física `Connection` mediante `DriverManager.getConnection(URL, USER, PASS)`.
2. **Modelo (`Cliente.java`):** Es un POJO (*Plain Old Java Object*) limpio, sin anotaciones ni dependencias de librerías externas.
3. **Acceso a Datos (`ClienteDAO.java`):**
   * El programador escribe las sentencias SQL literales (`"INSERT INTO cliente(...) VALUES (?, ?, ?)"`).
   * Asigna parámetros uno a uno (`ps.setString(1, c.getNombre())`).
   * Ejecuta y recorre fila a fila el `ResultSet` para construir manualmente cada objeto `Cliente` (`new Cliente(rs.getInt("id"), ...)`).
   * Debe gestionar manualmente las claves autogeneradas (`Statement.RETURN_GENERATED_KEYS`).

### B. ¿Qué es el enfoque ORM (Object-Relational Mapping) con JPA?
Un **ORM** (como Hibernate, implementado a través del estándar **JPA** configurado en `META-INF/persistence.xml`) actúa como un traductor automático entre el mundo orientado a objetos de Java y el mundo relacional de SQL:
1. **Configuración y Fábrica (`persistence.xml` y `JpaUtil.java`):**
   * Los datos de conexión, el dialecto de PostgreSQL y el comportamiento del esquema se definen en `src/main/resources/META-INF/persistence.xml`.
   * `JpaUtil` mantiene una única instancia de `EntityManagerFactory` para toda la aplicación, a partir de la cual se obtienen objetos `EntityManager` (equivalentes a una sesión de trabajo con la BD).
2. **Entidad Mapeada (`ClienteEntidad.java`):**
   * La clase Java se vincula directamente con la tabla mediante anotaciones JPA:
     * `@Entity` y `@Table(name = "cliente")`: Indican que la clase representa una tabla.
     * `@Id` y `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Indican la clave primaria y que PostgreSQL genera el ID automáticamente.
     * `@Column(...)`: Vincula cada atributo Java con su columna SQL.
3. **Acceso a Datos (`ClienteDAOOrm.java` y `TransaccionesORM.java`):**
   * **Sin SQL para operaciones CRUD básicas:** Para guardar, buscar, actualizar o borrar se invocan métodos orientados a objetos sobre el `EntityManager` (`em.persist(entidad)`, `em.find(...)`, `em.merge(entidad)`, `em.remove(entidad)`). El ORM genera el SQL por debajo.
   * **Consultas JPQL:** Para listados o filtros complejos se usa **JPQL** (*Jakarta Persistence Query Language*), que consulta sobre nombres de clases y atributos Java (`SELECT c FROM ClienteEntidad c`) en lugar de nombres de tablas y columnas SQL.
   * **Control Transaccional (`EntityTransaction`):** Toda operación de escritura requiere iniciar y confirmar una transacción (`tx.begin()` ... `tx.commit()`, o `tx.rollback()` si ocurre un error), tal como se demuestra en `TransaccionesORM.java` y `PruebaCRUDTransacciones.java`.

---

## 4. Flujo Comparativo de una Operación (`Insertar Cliente`)

```text
[ENFOQUE DAO + JDBC (ClienteDAO.java)]
  1. Abrir Connection con DriverManager
  2. Preparar SQL: "INSERT INTO cliente(nombre, email, activo) VALUES (?, ?, ?)"
  3. ps.setString(1, ...); ps.setString(2, ...); ps.setBoolean(3, ...);
  4. ps.executeUpdate() con RETURN_GENERATED_KEYS
  5. Leer ResultSet de claves -> cliente.setId(claves.getInt(1))
  6. Cerrar ResultSet, PreparedStatement y Connection

[ENFOQUE ORM + JPA (ClienteDAOOrm.java)]
  1. Obtener EntityManager desde JpaUtil.getEntityManager()
  2. Iniciar transacción: em.getTransaction().begin()
  3. Persistir objeto: em.persist(clienteEntidad)
  4. Confirmar transacción: em.getTransaction().commit()
     (El ORM ejecuta el INSERT y rellena automáticamente el campo @Id del objeto)
  5. Cerrar EntityManager: em.close()
```

---

## 5. Tabla Comparativa: DAO (JDBC) vs. ORM (JPA)

| Característica | DAO Tradicional (JDBC) | ORM (JPA / Hibernate) |
| :--- | :--- | :--- |
| **Clases del proyecto** | `ConexionBD`, `Cliente`, `ClienteDAO`, `VentanaPrincipal` | `JpaUtil`, `persistence.xml`, `ClienteEntidad`, `ClienteDAOOrm`, `TransaccionesORM`, `VentanaPrincipalORM` |
| **Lenguaje de consulta** | **SQL nativo** dependiente del motor (PostgreSQL). | **JPQL** (orientado a objetos, independiente del motor) o métodos del `EntityManager`. |
| **Mapeo Objeto <-> Tabla** | **Manual:** El programador lee el `ResultSet` columna a columna. | **Automático:** Mediante anotaciones (`@Entity`, `@Id`, `@Column`) en `ClienteEntidad`. |
| **Configuración de BD** | Constantes Java en `ConexionBD.java`. | Fichero estándar `META-INF/persistence.xml`. |
| **Objeto central de trabajo** | `Connection`, `PreparedStatement`, `ResultSet`. | `EntityManagerFactory`, `EntityManager`, `EntityTransaction`. |
| **Gestión de IDs generados** | Manual con `Statement.RETURN_GENERATED_KEYS` y `getGeneratedKeys()`. | Automática mediante `@GeneratedValue(strategy = GenerationType.IDENTITY)` al hacer `commit()`. |
| **Gestión de transacciones** | Auto-commit activado por defecto (o manual con `con.setAutoCommit(false)`). | **Obligatoria y explícita** (`begin()`, `commit()`, `rollback()`) para cualquier escritura. |
| **Curva de aprendizaje y código** | Más código repetitivo (*boilerplate*), pero control total y máximo rendimiento en consultas complejas. | Menos código CRUD, portabilidad entre bases de datos, pero requiere entender el ciclo de vida de las entidades (*Managed*, *Detached*) y transacciones. |

---

## 6. Resumen de Cuándo Usar Cada Uno

* **Usar DAO con JDBC (`ClienteDAO`)** cuando se necesita control absoluto sobre el SQL ejecutado, optimización extrema de consultas masivas o proyectos pequeños donde añadir un framework ORM supone demasiada complejidad.
* **Usar ORM con JPA (`ClienteDAOOrm`)** en aplicaciones empresariales con modelos de dominio ricos y múltiples relaciones entre entidades, donde se busca reducir el código repetitivo de mapeo, mantener transacciones seguras (`TransaccionesORM`) y facilitar el cambio de gestor de base de datos sin reescribir consultas.