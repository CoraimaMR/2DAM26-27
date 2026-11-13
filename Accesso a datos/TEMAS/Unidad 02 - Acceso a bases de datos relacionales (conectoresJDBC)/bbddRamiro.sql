-- =========================================================
-- UD2 - ACCESO A DATOS
-- Base de datos: tienda_ramiro
-- =========================================================


-- =========================================================
-- TABLA CLIENTE
-- =========================================================

CREATE TABLE cliente (

    id INTEGER GENERATED ALWAYS AS IDENTITY,

    nombre VARCHAR(100) NOT NULL,

    email VARCHAR(150) NOT NULL,

    activo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_cliente
        PRIMARY KEY (id),

    CONSTRAINT uq_cliente_email
        UNIQUE (email)
);


-- =========================================================
-- TABLA PRODUCTO
-- =========================================================

CREATE TABLE producto (

    id INTEGER GENERATED ALWAYS AS IDENTITY,

    nombre VARCHAR(120) NOT NULL,

    precio NUMERIC(10,2) NOT NULL,

    stock INTEGER NOT NULL DEFAULT 0,

    CONSTRAINT pk_producto
        PRIMARY KEY (id),

    CONSTRAINT ck_producto_precio
        CHECK (precio >= 0),

    CONSTRAINT ck_producto_stock
        CHECK (stock >= 0)
);


-- =========================================================
-- TABLA MOVIMIENTO STOCK
-- =========================================================

CREATE TABLE movimiento_stock (

    id BIGINT GENERATED ALWAYS AS IDENTITY,

    producto_id INTEGER NOT NULL,

    cantidad INTEGER NOT NULL,

    fecha TIMESTAMP NOT NULL
        DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_movimiento_stock
        PRIMARY KEY (id),

    CONSTRAINT fk_movimiento_producto
        FOREIGN KEY (producto_id)
        REFERENCES producto(id)
);


-- =========================================================
-- PERMISOS
-- =========================================================

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO tienda_ramiro;


GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO tienda_ramiro;


-- =========================================================
-- DATOS DE PRUEBA
-- =========================================================

INSERT INTO cliente (
    nombre,
    email,
    activo
)
VALUES
    ('Ana Ruiz', 'ana@ejemplo.com', TRUE),
    ('Marta López', 'marta@ejemplo.com', TRUE),
    ('Pedro García', 'pedro@ejemplo.com', FALSE);


INSERT INTO producto (
    nombre,
    precio,
    stock
)
VALUES
    ('Teclado mecánico', 59.90, 10),
    ('Ratón inalámbrico', 24.95, 25),
    ('Monitor 27 pulgadas', 229.99, 8),
    ('Auriculares USB', 39.50, 15);


-- =========================================================
-- COMPROBACIÓN
-- =========================================================

SELECT *
FROM cliente
ORDER BY id;


SELECT *
FROM producto
ORDER BY id;