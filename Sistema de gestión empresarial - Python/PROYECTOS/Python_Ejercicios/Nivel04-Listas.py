
'''Ejercicio 19
Solicita 10 números y almacénalos en una lista.
Muestra:
•	Mayor
•	Menor
•	Media'''
print(f"\n------------ EJERCICIO 19 ------------")
numeros = []

for i in range(10):
    numero = float(input(f"Introduce el número {i + 1}: "))
    numeros.append(numero)

print(f"Lista: {numeros}")
print(f"Mayor: {max(numeros)}")
print(f"Menor: {min(numeros)}")
print(f"Media: {sum(numeros) / len(numeros)}")

'''Ejercicio 20
Elimina los elementos duplicados de una lista.'''
print(f"\n------------ EJERCICIO 20 ------------")
lista_con_duplicados = [1, 2, 3, 2, 4, 1, 5, 3]
lista_sin_duplicados = [list(dict.fromkeys(lista_con_duplicados))]

print(f"Lista original: {lista_con_duplicados}")
print(f"Lista sin duplicados: {lista_sin_duplicados}")

'''Ejercicio 21
Ordena una lista de menor a mayor.'''
print(f"\n------------ EJERCICIO 21 ------------")
lista_desordenada = [8, 3, 12, 1, 6, 4]
lista_ordenada = sorted(lista_desordenada)

print(f"Lista original: {lista_desordenada}")
print(f"Lista ordenada: {lista_ordenada}")

'''Ejercicio 22
Busca un valor dentro de una lista e indica la posición.'''
print(f"\n------------ EJERCICIO 22 ------------")
lista_busqueda = [10, 25, 30, 45, 50]
valor = int(input("Introduce el valor que quieres buscar: "))

if valor in lista_busqueda:
    posicion = lista_busqueda.index(valor)
    print(f"El valor {valor} está en la posición {posicion}.")
else:
    print(f"El valor {valor} no está en la lista.")

'''Ejercicio 23
Crea una lista con los cuadrados del 1 al 20 usando comprensión de listas.'''
print(f"\n------------ EJERCICIO 23 ------------")
cuadrados = [numero ** 2 for numero in range(1, 21)]

print(f"Cuadrados del 1 al 20: {cuadrados}")

'''Ejercicio 24
Genera una lista con solo los números pares del 1 al 100 usando comprensión.'''
print(f"\n------------ EJERCICIO 24 ------------")
pares = [numero for numero in range(1, 101) if numero % 2 == 0]

print(f"Números pares del 1 al 100: {pares}")