# Enunciados de la unidad. Los ejercicios se dejan sin resolver.

# Ejercicio 01: Conversión de Temperaturas
#
# Escribe una función que convierta grados Celsius a Fahrenheit y viceversa. Prueba la función con varios valores.
#
print(f"\n------------ EJERCICIO 01 ------------")

# Ejercicio 02: Validación de Emails
#
# Implementa una función que reciba un string y devuelva `True` si es un email válido (contiene `@` y un punto), y `False` en caso contrario.
#
print(f"\n------------ EJERCICIO 02 ------------")

# Ejercicio 03: Cálculo de Media y Mediana
#
# Crea una función que reciba una lista de números y devuelva la media y la mediana.
#
print(f"\n------------ EJERCICIO 03 ------------")

# Ejercicio 04: Contador de Palabras
#
# Escribe una función que cuente cuántas palabras hay en un texto recibido como parámetro.
#
print(f"\n------------ EJERCICIO 04 ------------")

# Ejercicio 05: Generador de Contraseñas Aleatorias
#
# Implementa una función que genere una contraseña aleatoria de una longitud dada, usando letras, números y símbolos.
#
print(f"\n------------ EJERCICIO 05 ------------")

# Ejercicio 06: Filtrado de Números Pares
#
# Crea una función que reciba una lista de números y devuelva una nueva lista solo con los números pares.
#
print(f"\n------------ EJERCICIO 06 ------------")

# Ejercicio 07: Conversión de Fechas
#
# Escribe una función que reciba una fecha en formato `dd/mm/yyyy` y la devuelva en formato `yyyy-mm-dd`.
#
print(f"\n------------ EJERCICIO 07 ------------")

# Ejercicio 08: Búsqueda de Palabra en Texto
#
# Implementa una función que indique si una palabra dada aparece en un texto (ignorando mayúsculas/minúsculas).
#
print(f"\n------------ EJERCICIO 08 ------------")

# Ejercicio 09: Cálculo de Factorial
#
# Escribe una función recursiva que calcule el factorial de un número.
#
print(f"\n------------ EJERCICIO 09 ------------")

# Ejercicio 10: Formateo de Nombres
#
# Crea una función que reciba un nombre completo y lo devuelva en formato
print(f"\n------------ EJERCICIO 10 ------------")

# Ejercicio 11: Buscar 4 ambulancias
#
# Diseña una función en Python que, dados cinco puntos en el plano cartesiano,
# determine cuál de los últimos cuatro es el más cercano al primero.**
# Cada punto se representará mediante dos variables: una para la coordenada **x** (abscisa)
# y otra para la coordenada **y** (ordenada). Para calcular la distancia entre dos puntos,
# utilizarás la fórmula euclidiana:
# $$
# \text{distancia} = \sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2}
# $$
# Imagina que el primer punto representa la ubicación de un accidente,
# y los otros cuatro son las posiciones actuales de cuatro ambulancias.
# Tu función debe identificar cuál es la ambulancia más cercana al lugar del incidente,
# basándose en la distancia mínima.
print(f"\n------------ EJERCICIO 11 ------------")

# Ejercicio 12: Buscar Ambulancias
#
# Refactoriza el ejercicio anterior para que la función reciba una lista de puntos en lugar de cinco variables individuales,
# ya que habrá más o menos de cuatro ambulancias.
#
print(f"\n------------ EJERCICIO 12 ------------")

# Ejercicio 13: Ordenar por la suma de cifras
#
# Dada una lista de números enteros positivos, ordénalos de menor a mayor según la suma de sus cifras utilizando sorted() y una función lambda.
# Ejemplo de entrada:
# `numeros = [91, 43, 12, 85, 34]`
# Salida esperada:
# `[12, 21, 34, 91, 85]`  # ordenados por suma de cifras: 3, 6, 7, 10, 13
#
print(f"\n------------ EJERCICIO 13 ------------")

# Ejercicio 14: Filtrar palíndromos
#
# Dada una lista de palabras, utiliza filter() y una función lambda para quedarte solo con aquellas que son palíndromos (se leen igual al derecho que al revés).
# Ejemplo de entrada:
# `palabras = ['oso', 'python', 'reconocer', 'casa', 'radar']`
# Salida esperada:
# `['oso', 'reconocer', 'radar']`
#
print(f"\n------------ EJERCICIO 14 ------------")

# Ejercicio 15: Reducir a la cadena más larga
#
# Utiliza functools.reduce() y una función lambda para encontrar la cadena más larga de una lista de strings.
# Ejemplo de entrada:
# `nombres = ['Ana', 'Cristina', 'Luis', 'Beatriz']`
# Salida esperada:
# `'Cristina'`
#
print(f"\n------------ EJERCICIO 15 ------------")

# Ejercicio 16: Transformar y filtrar con map + filter
#
# Dada una lista de números enteros, usa map() con lambda para elevarlos al cuadrado, y luego filter() con lambda para quedarte solo con los que sean mayores que 100.
# Ejemplo de entrada:
# `valores = [4, 10, 11, 5, 13]`
# Salida esperada:
# `[121, 169]`
#
print(f"\n------------ EJERCICIO 16 ------------")

# Ejercicio 17: Ranking por eficiencia
#
# Dada una lista de tuplas (nombre_empleado, tiempo_en_segundos),
# crea un ranking usando sorted() y lambda por eficiencia (menos tiempo primero).
# Si empatan, ordena alfabéticamente.
# `tiempos = [('Luis', 320), ('Ana', 300), ('Carlos', 300), ('Bea', 400)]`
# Salida esperada:
# `[('Ana', 300), ('Carlos', 300), ('Luis', 320), ('Bea', 400)]`
#
print(f"\n------------ EJERCICIO 17 ------------")

# Ejercicio 18: Sistema de votación ponderada por antigüedad
#
# Enunciado:
# Crea una función que reciba un diccionario donde las claves son nombres de empleados y
# los valores son listas de tuplas (años_antigüedad, voto), donde voto es "sí" o "no".
# La función debe calcular el resultado de una votación interna ponderando cada voto
# por los años de antigüedad del empleado.
# Devuelve "sí" si el total ponderado de votos a favor supera al de votos en contra.
#
print(f"\n------------ EJERCICIO 18 ------------")

# Ejercicio 19: Sistema de gestión de inventario
#
# Crea una función que reciba un diccionario representando un inventario de productos,
# donde las claves son nombres de productos y los valores son tuplas (cantidad, precio_unitario).
# La función debe devolver una lista de tuplas ordenada por el valor total del inventario de cada producto (cantidad * precio_unitario) de mayor a menor.
print(f"\n------------ EJERCICIO 19 ------------")

# Ejercicio 20: Comparación de vocabularios con conjuntos
#
# Dadas dos cadenas de texto, escribe una función que devuelva:
# Las palabras únicas de cada texto.
# Las palabras en común entre ambos textos.
# Todas las palabras ordenadas alfabéticamente (sin duplicados).
# Usa set() y sorted() para resolverlo.
print(f"\n------------ EJERCICIO 20 ------------")

# Ejercicio 21: Gestionar ingresos y gastos
#
# Realizar un programa que gestione los ingresos y gastos mensuales en un año.
# Crea una función para leer del teclado los ingresos y gastos de cada mes del año.
# Crea otra función que a partir de los ingresos y gastos de un año, calcule el balance de cada mes.
# Las funciones no deben escribir nada por pantalla.
# El programa debe leer los datos con la primera función,
# realizar los cálculos con la segunda
# y finalmente mostrar los resultados.
#
print(f"\n------------ EJERCICIO 21 ------------")

# Ejercicio 22: Gestionar ingresos y gastos por ubicación
#
# Ampliar la funcionalidad del programa del problema 21 permitiendo gestionar los ingresos y gastos
# anuales por ciudad y código postal.
# Modificar la función de lectura de datos convenientemente, así como la función de cálculo de balances
# Además, realizar una tercera función que recibe los balances por ciudad, código postal y mes y
# devuelve los balances con valor mínimo y máximo,
# así como a qué ciudades, códigos postales y meses corresponden.
# Modifica el programa para que lea los datos, realice los cálculos y presente todos los resultados descritos.
#
print(f"\n------------ EJERCICIO 22 ------------")

# Ejercicio 23: Jugar al Monopoly
#
# Escribir un programa descompuesto convenientemente en base a funciones que, para una suma de dinero
# dada, indique cómo descomponerla en billetes y monedas corrientes. Se debe utilizar el mínimo posible de billetes y
# monedas. No existen limitaciones respecto al número de billetes y monedas disponibles.
#
print(f"\n------------ EJERCICIO 23 ------------")

# Ejercicio 24: Dar la campanada
#
# Crear un programa que lea del teclado dos valores que indican hora de comienzo y hora de fin (ej: 16:30
# y 19:17), y presente por pantalla la cantidad de veces que un reloj emite campanadas (las campanas se producen en los
# minutos: 0, 15, 30 y 45). Se supone que la hora de comienzo es menor que la hora de final y ambas pertenecen al mismo
# día.
# Por ejemplo, si la hora de comienzo es 9:44 y la hora de fin es 10:01, hay que informar de 2 campanadas.
# Si la hora de comienzo es 8:00 y la hora de fin es 10:07, el programa debe indicar que sonaron 8 campanadas.
#
print(f"\n------------ EJERCICIO 24 ------------")

# Ejercicio 25: Tiempo entre dos fechas
#
# Escribe una función que reciba dos fechas en formato `dd/mm/yyyy` y calcule cuántos días han pasado entre ambas. Valida que la segunda fecha sea posterior a la primera.
#
print(f"\n------------ EJERCICIO 25 ------------")

# Ejercicio 26: Gestión de notas con condiciones
#
# Implementa una función que reciba una lista de calificaciones (entre 0 y 10) y devuelva un resumen con: cuántos suspensos, cuántos aprobados, la media y la nota más repetida.
#
print(f"\n------------ EJERCICIO 26 ------------")

# Ejercicio 27: Descomposición de importe en billetes y monedas
#
# Crea una función que reciba una cantidad de dinero en euros (por ejemplo, 186.73) y devuelva cuántos billetes y monedas de cada tipo serían necesarios para representar esa cantidad usando el menor número de unidades.
#
print(f"\n------------ EJERCICIO 27 ------------")

# Ejercicio 28: Estadísticas de temperaturas por semana
#
# Diseña una función que reciba un diccionario con semanas como claves (por ejemplo, `"Semana 1"`, `"Semana 2"`, etc.) y listas de temperaturas diarias como valores. La función debe devolver un resumen con la temperatura media por semana y la semana más cálida.
#
print(f"\n------------ EJERCICIO 28 ------------")

# Ejercicio 29: Análisis de actividad horaria
#
# Recibe una lista de horas en las que se ha producido actividad en un sistema (por ejemplo, `[9, 10, 10, 11, 14, 9, 17]`). Devuelve el rango horario con más actividad (hora y cantidad de ocurrencias).
#
print(f"\n------------ EJERCICIO 29 ------------")

# Ejercicio 30: Resumen de texto con estadísticas
#
# Implementa una función que reciba un texto y devuelva un resumen con el número total de palabras, número total de frases, palabra más larga, y cuántas veces aparece cada palabra (ignorando mayúsculas y signos de puntuación).
#
print(f"\n------------ EJERCICIO 30 ------------")

# Ejercicio 31: Clasificación de productos por categoría
#
# Escribe una función que reciba una lista de productos, donde cada producto es una tupla (nombre, categoría, precio). Devuelve un diccionario que agrupe los productos por categoría y calcule el precio medio por cada una.
#
print(f"\n------------ EJERCICIO 31 ------------")

# Ejercicio 32: Agrupación de resultados deportivos
#
# Crea una función que reciba una lista de resultados de partidos con el formato `(equipo1, equipo2, goles1, goles2)` y devuelva una tabla con puntos por equipo (3 por victoria, 1 por empate).
#
print(f"\n------------ EJERCICIO 32 ------------")

# Ejercicio 33: Control de acceso con intentos limitados
#
# Escribe una función que simule un sistema de login con nombre de usuario y contraseña. Solo se permiten tres intentos. La función debe devolver `True` si se accede correctamente y `False` si se exceden los intentos.
#
print(f"\n------------ EJERCICIO 33 ------------")

# Ejercicio 34: Conversor horario con validación
#
# Crea una función que reciba una hora en formato `hh:mm` y devuelva la hora equivalente en formato de 12 horas con "am" o "pm". Valida que el formato sea correcto.
#
print(f"\n------------ EJERCICIO 34 ------------")

# Ejercicio 35: Ranking de ventas por empleado
#
# Dado un diccionario con empleados como claves y listas de importes de ventas como valores, implementa una función que devuelva un ranking de mayor a menor venta total, con nombre y total acumulado.
#
print(f"\n------------ EJERCICIO 35 ------------")

# Ejercicio 36: Sistema de matrícula de vehículos
#
# Diseña una función que valide si una matrícula española es correcta (formato: cuatro dígitos y tres letras, sin vocales). Luego, otra función que reciba una lista de matrículas y devuelva cuáles son válidas.
#
print(f"\n------------ EJERCICIO 36 ------------")

# Ejercicio 37: Mapa de calor de asistencia
#
# Crea una función que reciba un diccionario con días de la semana como claves y número de asistentes como valores. Devuelve un gráfico textual de barras (`*`) proporcional a la cantidad de asistentes por día.
#
print(f"\n------------ EJERCICIO 37 ------------")

# Ejercicio 38: Informe de stock de tienda
#
# Escribe una función que reciba un diccionario con productos como claves y tuplas `(stock, precio_unitario)` como valores. Devuelve una lista de productos agotados, el valor total del inventario y el producto más valioso.
#
print(f"\n------------ EJERCICIO 38 ------------")

# Ejercicio 39: Simulación de lanzamientos de dado
#
# Crea una función que simule lanzar un dado n veces y devuelva un diccionario con la frecuencia de cada cara (del 1 al 6). Luego, genera una tabla con porcentajes de aparición.
#
print(f"\n------------ EJERCICIO 39 ------------")

# Ejercicio 40: Control horario de fichajes
#
# Diseña una función que reciba una lista de horas de entrada y salida (tuplas `("08:45", "17:15")`) y calcule el número total de horas trabajadas al día y la media semanal. Valida los formatos.
#
print(f"\n------------ EJERCICIO 40 ------------")

# Ejercicio 41: Análisis de tráfico por tramo horario
#
# Recibe un diccionario donde las claves son tramos horarios (`"08-09"`, `"09-10"`, etc.) y los valores son listas con el número de vehículos registrados por día. Calcula la media por tramo y señala los picos de tráfico.
#
print(f"\n------------ EJERCICIO 41 ------------")

# Ejercicio 42: Clasificación de películas por género
#
# Crea una función que reciba una lista de películas en formato `(título, género, duración)` y devuelva un diccionario agrupado por género, con la media de duración y la lista de títulos ordenados alfabéticamente.
#
print(f"\n------------ EJERCICIO 42 ------------")

# Ejercicio 43: Calendario de eventos
#
# Diseña una función que reciba una lista de eventos (cada uno como tupla: `("nombre", "fecha", "hora")`) y devuelva un calendario ordenado cronológicamente. Valida el formato de fechas y horas.
#
print(f"\n------------ EJERCICIO 43 ------------")

# Ejercicio 44: Compresión de texto básica
#
# Crea una función que reciba un texto y lo comprima sustituyendo repeticiones consecutivas de caracteres por el carácter seguido del número de repeticiones (`"aaabbbc"` → `"a3b3c"`). Si no hay repeticiones, se devuelve igual.
#
print(f"\n------------ EJERCICIO 44 ------------")

# Ejercicio 45: Histograma de calificaciones
#
# Dada una lista de notas entre 0 y 10, implementa una función que construya un histograma textual con intervalos de 2 puntos (0–1, 2–3, ...). Cada barra del histograma se representa con `*`.
#
print(f"\n------------ EJERCICIO 45 ------------")

# Ejercicio 46: Analizador de respuestas de encuesta
#
# Recibe un diccionario donde las claves son preguntas y los valores listas de respuestas. La función debe mostrar para cada pregunta la respuesta más común y cuántas veces se repite.
#
print(f"\n------------ EJERCICIO 46 ------------")

# Ejercicio 47: Conversor numérico a texto
#
# Escribe una función que convierta un número entre 0 y 999 en su representación textual en castellano (`145` → `"ciento cuarenta y cinco"`).
#
print(f"\n------------ EJERCICIO 47 ------------")

# Ejercicio 48: Validación y análisis de DNI español
#
# Diseña una función que valide un DNI (8 dígitos + letra) y devuelva si es correcto. Otra función debe indicar el número de DNI válidos en una lista.
#
print(f"\n------------ EJERCICIO 48 ------------")

# Ejercicio 49: Monitor de sensores
#
# Recibe un diccionario con identificadores de sensores como claves y listas de medidas como valores. La función devuelve para cada sensor la media, el máximo, el mínimo y detecta si hay valores atípicos (fuera de 2 desviaciones estándar).
#
print(f"\n------------ EJERCICIO 49 ------------")

# Ejercicio 50: Resumen financiero por trimestre
#
# Dado un diccionario con meses como claves (`"Enero"`, `"Febrero"`, ...) y valores con ingresos y gastos, agrupa los datos por trimestre y devuelve el balance total por trimestre y el mes más rentable.
#
print(f"\n------------ EJERCICIO 50 ------------")

# Ejercicio 51: Buscador de coincidencias parciales
#
# Implementa una función que reciba una lista de nombres y un texto de búsqueda. Devuelve los nombres que contienen el texto buscado, sin importar mayúsculas o acentos.
#
print(f"\n------------ EJERCICIO 51 ------------")

# Ejercicio 52: Control de versiones de documentos
#
# Crea una función que reciba una lista de nombres de archivo con versión (`"informe_v1"`, `"informe_v2"`, etc.) y devuelva el nombre del documento con la versión más reciente.
#
print(f"\n------------ EJERCICIO 52 ------------")

# Ejercicio 53: Simulador de votos con validación
#
# Diseña una función que reciba votos (strings) y un conjunto de opciones válidas. Devuelve un recuento de votos por opción e ignora los no válidos, mostrando advertencias.
#
print(f"\n------------ EJERCICIO 53 ------------")

# Ejercicio 54: Tablero de puntuaciones de jugadores
#
# Recibe un diccionario donde las claves son jugadores y los valores listas de puntuaciones. La función debe devolver el ranking medio y el top 3 de mejores puntuaciones individuales.
#
print(f"\n------------ EJERCICIO 54 ------------")

# Ejercicio 55: Gestión de pedidos con descuentos
#
# Dado un carrito con productos en formato `(nombre, cantidad, precio_unitario)`, implementa una función que calcule el total, aplicando descuentos por cantidad si corresponde (por ejemplo, 10% a partir de 5 unidades).
#
print(f"\n------------ EJERCICIO 55 ------------")

# Ejercicio 56: Sistema de recomendaciones por afinidad
#
# Crea una función que reciba dos diccionarios: uno con usuarios y sus películas favoritas, y otro con películas recomendadas por género. Devuelve sugerencias personalizadas por usuario según sus gustos.
#
print(f"\n------------ EJERCICIO 56 ------------")

# Ejercicio 57: Control de cambios en un proyecto
#
# Recibe una lista de cambios en un proyecto, cada uno con una fecha y descripción. Crea una función que los ordene y resuma por semana, indicando cuántos cambios hubo cada una.
#
print(f"\n------------ EJERCICIO 57 ------------")

# Ejercicio 58: Agrupación de datos climáticos
#
# Dado un CSV (simulado como lista de tuplas) con fecha, ciudad y temperatura, agrupa los datos por ciudad y devuelve la temperatura media, mínima y máxima por ciudad.
#
print(f"\n------------ EJERCICIO 58 ------------")

# Ejercicio 59: Validador y formateador de IBAN
#
# Escribe una función que reciba un número de cuenta bancaria en formato IBAN (por ejemplo, `"ES7620770024003102575766"`) y verifique si cumple con el formato español (22 caracteres: empieza por `"ES"`, seguido de 20 dígitos).
# Si es válido, devuelve el IBAN formateado en bloques de 4 caracteres separados por espacio.
# Si no es válido, muestra un mensaje de error claro indicando el problema (longitud incorrecta, prefijo erróneo o caracteres no numéricos).
#
print(f"\n------------ EJERCICIO 59 ------------")

# Ejercicio 60: Resumen de asistencia por código QR
#
# Simula un sistema de control de asistencia mediante escaneos de códigos QR.
# Crea una función que reciba una lista de tuplas con el formato `(nombre, fecha, hora)` representando cada escaneo.
# La función debe generar un resumen por persona que indique:
# - Cuántos días ha asistido.
# - La fecha del primer y del último día en que asistió.
# - El número total de escaneos registrados.
# Ordena el resultado alfabéticamente por nombre y presenta los datos en un formato legible.
#
print(f"\n------------ EJERCICIO 60 ------------")
