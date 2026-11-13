# El Muro - Sistema de Cuervos Mensajeros

## Contexto

La Guardia de la Noche necesita enviar mensajes entre distintas fortalezas del Norte utilizando cuervos mensajeros.

El proceso principal actuará como **Lord Comandante** y lanzará varios procesos hijos que actuarán como **Maestres Mensajeros**.

Cada maestre recibe órdenes mediante su entrada estándar y responde por su salida estándar.

---

# Protocolo de mensajes

Cada línea enviada a un maestre tendrá el formato:

```text
CASA nombre
```

Ejemplos:

```text
CASA Stark
CASA Lannister
CASA Targaryen
```

El maestre responderá:

```text
OK lema
```

o

```text
ERROR casa desconocida
```

Ejemplos:

```text
OK Winter is Coming
OK Hear Me Roar
OK Fire and Blood
```

---

# Casas válidas

| Casa | Lema |
|--------|--------|
| Stark | Winter is Coming |
| Lannister | Hear Me Roar |
| Targaryen | Fire and Blood |
| Baratheon | Ours is the Fury |
| Greyjoy | We Do Not Sow |

---

# Modos de ejecución

El programa tendrá dos modos:

```text
maestre <id>

comandar <fichero> <numMaestres>
```

---

# Funcionamiento de los maestres

Cuando el programa se ejecuta como:

```text
maestre 3
```

debe:

1. Leer líneas desde la entrada estándar.
2. Resolver cada mensaje recibido.
3. Escribir la respuesta en la salida estándar.
4. Registrar actividad por stderr.

Ejemplo:

```text
[T3] CASA Stark -> OK Winter is Coming
```

---

# Comandos especiales

## FIN

Al recibir:

```text
FIN
```

responderá:

```text
STATS <id> <correctos> <errores>
```

y finalizará con código:

```text
0
```

---

## TRAICION

Al recibir:

```text
TRAICION
```

el maestre simulará una muerte inesperada:

```text
[T3] El maestre ha sido ejecutado por traición
```

y finalizará con código:

```text
7
```

---

# Fichero de entrada

Ejemplo:

```text
CASA Stark
CASA Lannister
CASA Bolton
CASA Targaryen
TRAICION
CASA Greyjoy
CASA Stark
```

---

# Tarea 1 (2 puntos)

Implementar:

```kotlin
fun resolverCasa(linea: String): String
```

Debe procesar la petición y generar la respuesta correspondiente.

---

# Tarea 2 (2 puntos)

Implementar:

```kotlin
fun lanzarMaestre(id: Int): Maestre
```

Debe:

- Crear el proceso hijo.
- Redirigir stderr a:

```text
logs/maestre-id.log
```

Ejemplo:

```text
logs/maestre-3.log
```

---

# Tarea 3 (2 puntos)

Implementar:

```kotlin
fun enviar(maestre: Maestre, mensaje: String): String?
```

Debe:

1. Escribir el mensaje al hijo.
2. Vaciar el buffer.
3. Leer la respuesta.
4. Devolver null si la comunicación falla.

---

# Tarea 4 (2 puntos)

El Lord Comandante debe repartir las órdenes en **round-robin**:

Ejemplo con 3 maestres:

```text
Orden 1 -> M1
Orden 2 -> M2
Orden 3 -> M3
Orden 4 -> M1
Orden 5 -> M2
Orden 6 -> M3
```

Si un maestre muere:

1. Detectar la caída.
2. Obtener su código de salida.
3. Lanzar otro con el mismo identificador.
4. Continuar el reparto.

Ejemplo:

```text
[T2] trabajador caído (código 7), relanzado PID 5432
```

---

# Tarea 5 (2 puntos)

Implementar:

```kotlin
fun cerrar(maestre: Maestre): String
```

Debe:

1. Enviar:

```text
FIN
```

2. Esperar hasta 2 segundos.
3. Forzar la finalización si no responde.
4. Devolver la línea de estadísticas.

---

# Ejemplo de ejecución

```text
Lanzados 3 maestres
```

```text
[T1] CASA Stark -> OK Winter is Coming
[T2] CASA Lannister -> OK Hear Me Roar
[T3] CASA Bolton -> ERROR casa desconocida
[T1] CASA Targaryen -> OK Fire and Blood
[T2] TRAICION -> maestre caído (código 7), relanzado
[T3] CASA Greyjoy -> OK We Do Not Sow
[T1] CASA Stark -> OK Winter is Coming
```

Resumen final:

```text
Resumen:

STATS 1 3 0 (codigo 0)
STATS 2 1 0 (codigo 0)
STATS 3 1 1 (codigo 0)

Órdenes procesadas: 7
Maestres caídos: 1
```

## Conceptos evaluados

- ProcessBuilder
- Variables de entorno
- Comunicación por tuberías (stdin/stdout)
- BufferedReader
- BufferedWriter
- Gestión de procesos hijos
- waitFor()
- destroyForcibly()
- Redirección de errores
- Round-robin
- Recuperación ante fallos
- Códigos de salida de procesos


