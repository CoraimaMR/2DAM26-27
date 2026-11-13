
''' Ejercicio 1
Pide al usuario su nombre y edad. Muestra un mensaje indicando cuántos años tendrá dentro de 10 años.'''
from uuid import MAX
nombre = input('Ingrese su nombre: ')
edad = int(input('Ingrese su edad: '))

print(f"--> En 10 años, {nombre} tendra {edad +10} años.\n")

'''Ejercicio 2
Solicita dos números y muestra:
•	Suma
•	Resta
•	Multiplicación
•	División
•	División entera
•	Resto'''
n1 = int(input("Ingrese un número: "))
n2 = int(input("Ingrese otro número: "))

print("- Suma: ", n1 + n2)
print("- Resta: ", n1 - n2)
print("- Multiplicacion: ", n1 * n2)
print("- División: ", n1 / n2)
print("- División entera: ", n1 // n2)
print("- Resto: ", n1 % n2)
print()

'''Ejercicio 3
Pide una temperatura en grados Celsius y conviértela a Fahrenheit.'''
celsius = float(input("Introduce la temperatura en grados Celsius: "))
fahrenheit = (celsius * 9/5) + 32

print(f"-->{celsius}°C equivalen a {fahrenheit}°F\n")

'''Ejercicio 4
Solicita un número y muestra si es par o impar.'''
n = int(input("Ingrese un número: "))

if n % 2 == 0: print("--> Par")
else: print("--> Impar")

'''Ejercicio 5
Pide tres números y muestra cuál es el mayor.'''
n3 = int(input("Ingrese un número: "))
n4 = int(input("Ingrese un segundo número: "))
n5 = int(input("Ingrese un tercer número: "))

print("--> El número mayor es: ", max(n3,n4,n5))