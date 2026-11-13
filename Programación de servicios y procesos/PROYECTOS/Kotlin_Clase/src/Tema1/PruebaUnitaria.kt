package Tema1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/* En este ejemplo, estamos probando una clase Calculator que tiene
un mét0do add. La prueba verifica que la suma de 2 y 3 sea igual a 5.
Para ejecutar las pruebas unitarias, podemos utilizar un entorno de desarrollo
 integrado (IDE) como IntelliJ IDEA, que tiene soporte integrado para JUnit.
También podemos ejecutar las pruebas desde la línea de comandos utilizando Gradle o Maven.
 */
class CalculatorTest {

    @Test
    fun suma() {
        assertEquals(4, 2 + 2)

    }
}