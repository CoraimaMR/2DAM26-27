select 
   t.typname,
   a.attname,
   a.attnum
FROM pg_attribute a
JOIN pg_type t
     ON t.typrelid = a.attrelid
WHERE t.typname = 'direccion_tipo'
      AND t.typnamespace = 'public'::regnamespace
	  AND NOT a.attisdropped
ORDER BY a.attnum;