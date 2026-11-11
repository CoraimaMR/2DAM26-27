-- CREATE ROLE aula_user LOGIN PASSWORD 'aula1234';

-- Creamos la database con la interfaz de pgAdmin

BEGIN;

CREATE TABLE cliente (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre text NOT NULL CHECK (length(trim(nombre)) > 0),
    email text NOT NULL UNIQUE CHECK (position('@' in email) > 1),
    ciudad text NOT NULL CHECK (length(trim(ciudad)) > 0)
);

INSERT INTO cliente(nombre, email, ciudad)
VALUES
    ('Elena Costa', 'elena@repaso.es', 'Vigo'),
    ('Pedro Martín', 'pedro@repaso.es', 'Lugo'),
    ('Sofía Álvarez', 'sofia@repaso.es', 'Ourense');

COMMIT;

SELECT * FROM repaso.cliente;