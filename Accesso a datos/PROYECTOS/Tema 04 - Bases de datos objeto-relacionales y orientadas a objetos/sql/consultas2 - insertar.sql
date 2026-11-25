INSERT INTO public.cliente ( nombre, email, direccion, telefonos, preferencias)
VALUES ('Armando Guerra', 'armando@gmail.com',
ROW(
'Calle de la amargura 15',
'Vigo',
'32450'
)::public.direccion_tipo,
ARRAY['646464646','646464664'],
'{"canal":"email","tema":"oscuro"}'::jsonb
)
RETURNING id;