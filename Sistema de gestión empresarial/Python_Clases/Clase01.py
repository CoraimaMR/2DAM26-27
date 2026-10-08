print("Hello world!!")

#Comentario linea

''' Comentario
        multilinea'''

pollito = ""

gallina = "Gallina"
print(gallina)
print(type(gallina))

IVA = 0.21
print(IVA)

nombre, apellido = "Coraima", "Mera"
print(nombre, apellido)

fruta, verdura, carne = ["Banana", "Berenjena", None]
print(fruta, verdura, carne)

print(0 not in [4,5,9,7])

if (2 == "2"):
    print("TRUE")
else:
    print("FALSE")


dia_semana = "Viernes"
if (dia_semana == "Lunes"):
    print("Nooo es lunes")
elif (dia_semana == "Viernes"):
    print("Siii es viernes")
else:
    print("Ya queda poco")

print("ESSS VIERNES" if (dia_semana == "Viernes") else "No es viernes :(")