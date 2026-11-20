
'''Ejercicio 11
Muestra los números del 1 al 100.'''
print(f"\n------------ EJERCICIO 11 ------------")
n = int(0)

while n != 100:
    n = int(n) + 1
    print(n)

'''Ejercicio 12
Muestra únicamente los números pares entre 1 y 100.'''
print(f"\n------------ EJERCICIO 12 ------------")
n2 = int(0)

while n2 != 100:
    n2 = n2 + 1
    if n2 % 2 == 0:
        print(n2)

'''Ejercicio 13
Calcula la suma de los números del 1 al N.'''
print(f"\n------------ EJERCICIO 13 ------------")
n3 = int(input("Introduzca un número: "))

for i in range(1,n3):
    n3 = n3 + i

print(n3)

'''Ejercicio 14
Pide una contraseña hasta que el usuario introduzca:
Python2026'''
print(f"\n------------ EJERCICIO 14 ------------")
contraseña = ""

while contraseña != "contraseña":
    contraseña = input("Ingrese la contraseña: ")

'''Ejercicio 15
Genera la tabla de multiplicar de un número.'''
print(f"\n------------ EJERCICIO 15 ------------")
n4 = int(input("Introduzca un número: "))

for i in range(1, 11):
    print(f"{n4} x {i} = {n4 * i}")

'''Ejercicio 16
Calcula el factorial de un número.'''
print(f"\n------------ EJERCICIO 16 ------------")
n5 = int(input("Introduzca un número: "))
factorial = 1

for i in range(1, n5 + 1):
    factorial *= i
print(f"El factorial de {n5} es {factorial}")

'''Ejercicio 17
Cuenta cuántas vocales tiene una frase.'''
print(f"\n------------ EJERCICIO 17 ------------")
frase = input("Introduzca una frase: ")
vocales = "aeiouAEIOU"
contador = 0

for letra in frase:
    if letra in vocales:
        contador += 1
print(f"La frase contiene {contador} vocales.")

'''Ejercicio 18
Invierte una cadena sin usar slicing.'''
print(f"\n------------ EJERCICIO 18 ------------")
cadena = input("Introduzca una cadena: ")
cadena_invertida = ""

for i in range(len(cadena) - 1, -1, -1):
    cadena_invertida += cadena[i]
print(f"La cadena invertida es: {cadena_invertida}")