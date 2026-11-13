
'''Ejercicio 6
Solicita una nota entre 0 y 10.
Muestra:
•	Suspenso
•	Aprobado
•	Bien
•	Notable
•	Sobresaliente'''
nota = int(input("Ingrese una nota: "))

if nota >= 0 and nota < 5:
    print("Suspenso")
elif nota < 6:
    print("Aprobado")
elif nota < 7:
    print("Bien")
elif nota < 9:
    print("Notable")
elif nota <= 10:
    print("Sobresaliente")
else:
    print("Número invalido")

'''Ejercicio 7
Pide un año y determina si es bisiesto.'''
año = int(input("Ingrese un año: "))

if (año % 4 == 0 and año % 100 != 0) or (año % 400 == 0):
    print(f"El año {año} es bisiesto.")
else:
    print(f"El año {año} no es bisiesto.")

'''Ejercicio 8
Solicita usuario y contraseña.
Valida:
usuario = "admin"
password = "1234"'''
usuario = input("Ingrese su usuario: ")
contraseña = input("Ingrese su contraseña: ")

if  usuario == "admin" and contraseña == "1234":
    print("Usuario y contraseña correcta.")
else:
    print("Usuario o contraseña no correcta.")

'''Ejercicio 9
Pide un número del 1 al 7 y muestra el día de la semana usando match.'''
nSemana = int(input("Ingrese un número de a la semana: "))

if 1 <= nSemana <= 7:
    match nSemana:
        case 1:
            print("Lunes")
        case 2:
            print("Martes")
        case 3:
            print("Miércoles")
        case 4:
            print("Jueves")
        case 5:
            print("Viernes")
        case 6:
            print("Sábado")
        case 7:
            print("Domingo")
else:
    print("Error: El número debe estar entre 1 y 7.")

'''Ejercicio 10
Solicita el precio de un producto y aplica:
•	5% descuento si supera 100 €
•	10% si supera 500 €'''
precio = int(input("Ingrese el precio de un producto: "))

if precio < 100:
    print("No hay descuento: ", precio)
elif precio >= 100 and precio < 500:
    print("Descuento del 5%: ", precio+(precio*0.05))
elif precio >= 500:
    print("Descuento del 10%: ", precio+(precio*0.1))