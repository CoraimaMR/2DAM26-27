-- ================================================================
-- CLIENTES JAVAFX: INSTALACION PARA PGADMIN QUERY TOOL
-- Todos los objetos de la aplicacion se crean en el esquema public.
-- IMPORTANTE: ejecutar en DOS FASES, NO todo de una sola vez.
-- FASE 1: abrir Query Tool de la base postgres, ejecutar SOLO bloque 1.
-- FASE 2: abrir Query Tool de aula_objetos, ejecutar SOLO bloque 2.
-- Requiere una cuenta administradora para crear usuario/base y
-- modificar propietarios. No elimina datos existentes.
-- ================================================================

-- =================== INICIO FASE 1 =============================
-- Ejecutar SOLO este bloque conectado a la base postgres.
-- 1.1 Crear usuario si no existe (SQL valido en Query Tool).
DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_roles WHERE rolname = 'aula_user') THEN
    CREATE ROLE aula_user LOGIN PASSWORD 'AulaFx_2026';
  END IF;
END
$$;

-- 1.2 Configurar las credenciales para este entorno de clase.
ALTER ROLE aula_user LOGIN PASSWORD 'AulaFx_2026';

-- 1.3 Ejecutar esta orden POR SEPARADO y SOLO si la base no existe.
-- IMPORTANTE: CREATE DATABASE no admite DO ni transacciones.
-- Verifica antes si ya existe con:
SELECT datname FROM pg_database WHERE datname = 'aula_objetos';

-- DESCOMENTA LA SIGUIENTE LINEA SI NO EXISTE LA BASE, y ejecútala sola:
-- CREATE DATABASE aula_objetos OWNER aula_user ENCODING 'UTF8' TEMPLATE template0;

-- ==================== FIN FASE 1 ===============================
-- Una vez creada la base, abre su Query Tool y ejecuta desde aqui.

-- =================== INICIO FASE 2 =============================
-- Esta comprobacion debe devolver aula_objetos; si no, DETENTE.
SELECT current_database() AS base_actual;

-- 2.1 Permisos para el usuario de la aplicacion.
GRANT CONNECT ON DATABASE aula_objetos TO aula_user;
GRANT USAGE, CREATE ON SCHEMA public TO aula_user;
SET search_path TO public;

-- 4. Tipo compuesto: se crea si no existe.
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM pg_type t JOIN pg_namespace n ON n.oid=t.typnamespace
    WHERE n.nspname='public' AND t.typname='direccion_tipo'
  ) THEN
    CREATE TYPE public.direccion_tipo AS (
      calle text,
      ciudad text,
      codigo_postal text
    );
  END IF;
END $$;

-- 5. Tabla de repaso: nombre distinto para evitar colisión en public.
CREATE TABLE IF NOT EXISTS public.cliente_repaso (
  id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  nombre text NOT NULL CHECK (length(trim(nombre)) > 0),
  email text NOT NULL UNIQUE CHECK (position('@' in email) > 1),
  ciudad text NOT NULL CHECK (length(trim(ciudad)) > 0)
);

-- 6. Tabla objeto-relacional.
CREATE TABLE IF NOT EXISTS public.cliente (
  id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  nombre text NOT NULL CHECK (length(trim(nombre)) > 0),
  email text NOT NULL UNIQUE CHECK (position('@' in email) > 1),
  direccion public.direccion_tipo NOT NULL,
  telefonos text[] NOT NULL DEFAULT ARRAY[]::text[],
  preferencias jsonb NOT NULL DEFAULT '{"canal":"email"}'::jsonb,
  CONSTRAINT cliente_direccion_calle_chk CHECK ((direccion).calle IS NOT NULL AND length(trim((direccion).calle)) > 0),
  CONSTRAINT cliente_direccion_ciudad_chk CHECK ((direccion).ciudad IS NOT NULL AND length(trim((direccion).ciudad)) > 0),
  CONSTRAINT cliente_direccion_cp_chk CHECK ((direccion).codigo_postal IS NOT NULL AND (direccion).codigo_postal ~ '^[0-9]{5}$'),
  CONSTRAINT cliente_telefonos_dimension_chk CHECK (array_ndims(telefonos) IS NULL OR array_ndims(telefonos) = 1),
  CONSTRAINT cliente_telefonos_no_null_chk CHECK (array_position(telefonos, NULL) IS NULL),
  CONSTRAINT cliente_preferencias_objeto_chk CHECK (jsonb_typeof(preferencias) = 'object'),
  CONSTRAINT cliente_preferencias_canal_chk CHECK (
    preferencias ? 'canal' AND
    jsonb_typeof(preferencias->'canal') = 'string' AND
    preferencias->>'canal' IN ('email','telefono')
  )
);

-- 7. Datos de ejemplo, sin duplicar correos al ejecutar de nuevo.
INSERT INTO public.cliente_repaso(nombre,email,ciudad) VALUES
 ('Elena Costa','elena@repaso.es','Vigo'),
 ('Pedro Martín','pedro@repaso.es','Lugo'),
 ('Sofía Álvarez','sofia@repaso.es','Ourense')
ON CONFLICT (email) DO NOTHING;

INSERT INTO public.cliente(nombre,email,direccion,telefonos,preferencias) VALUES
 ('Ana López','ana@aula.es',ROW('Calle Sol, 10','Vigo','36201')::public.direccion_tipo,ARRAY['600111222','986111222'],'{"canal":"email","idioma":"es"}'),
 ('Luis Pérez','luis@aula.es',ROW('Plaza Mayor, 2','Madrid','28001')::public.direccion_tipo,ARRAY['600333444'],'{"canal":"telefono","idioma":"es"}'),
 ('Marta Díaz','marta@aula.es',ROW('Rúa Nova, 5','Santiago','15701')::public.direccion_tipo,ARRAY[]::text[],'{"canal":"email","intereses":["java","sql"]}')
ON CONFLICT (email) DO NOTHING;

-- 8. Índice para consultas de contención JSONB.
CREATE INDEX IF NOT EXISTS cliente_preferencias_gin
ON public.cliente USING gin(preferencias);

-- 9. Procedimiento: modifica solo el atributo ciudad del compuesto.
CREATE OR REPLACE PROCEDURE public.cambiar_ciudad(IN p_id bigint, IN p_ciudad text)
LANGUAGE plpgsql AS $$
BEGIN
  IF p_ciudad IS NULL OR length(trim(p_ciudad)) = 0 THEN
    RAISE EXCEPTION 'La ciudad no puede estar vacía' USING ERRCODE = '22023';
  END IF;
  UPDATE public.cliente SET direccion.ciudad = trim(p_ciudad) WHERE id = p_id;
  IF NOT FOUND THEN
    RAISE EXCEPTION 'No existe el cliente %', p_id USING ERRCODE = 'P0002';
  END IF;
END;
$$;

-- 10. Función: devuelve el número de clientes avanzados.
CREATE OR REPLACE FUNCTION public.total_clientes()
RETURNS bigint LANGUAGE sql STABLE
AS $$ SELECT count(*) FROM public.cliente $$;

-- 11. Propiedad y privilegios para el usuario de la aplicación.
ALTER TYPE public.direccion_tipo OWNER TO aula_user;
ALTER TABLE public.cliente_repaso OWNER TO aula_user;
ALTER TABLE public.cliente OWNER TO aula_user;
ALTER PROCEDURE public.cambiar_ciudad(bigint,text) OWNER TO aula_user;
ALTER FUNCTION public.total_clientes() OWNER TO aula_user;
GRANT USAGE ON SCHEMA public TO aula_user;
GRANT SELECT, INSERT, UPDATE, DELETE ON public.cliente_repaso, public.cliente TO aula_user;
GRANT EXECUTE ON PROCEDURE public.cambiar_ciudad(bigint,text) TO aula_user;
GRANT EXECUTE ON FUNCTION public.total_clientes() TO aula_user;

-- 12. Comprobaciones finales.
SELECT current_database() AS base_de_datos, current_schema() AS esquema_actual;
SELECT 'cliente_repaso' AS tabla, count(*) AS filas FROM public.cliente_repaso
UNION ALL
SELECT 'cliente' AS tabla, count(*) AS filas FROM public.cliente;
SELECT public.total_clientes() AS total_clientes_avanzados;

-- ===================== FIN FASE 2 ==============================
