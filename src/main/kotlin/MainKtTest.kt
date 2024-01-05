import com.sun.tools.javac.Main
import org.example.BilletesZonas
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class MainKtTest {

    @Test
    fun calcularPrecio() {
        var RESULT = 14.896875
        Assertions.assertEquals(org.example.calcularPrecio(BilletesZonas(1, 1)), RESULT)
        RESULT = 73.772
        Assertions.assertEquals(org.example.calcularPrecio(BilletesZonas(2, 2)), RESULT)
        RESULT =  2.4
        Assertions.assertEquals(org.example.calcularPrecio(BilletesZonas(0, 0)), RESULT)
    }

    @Test
    fun precioTotal() {
        var arrayPrueba:ArrayList<BilletesZonas> = arrayListOf(BilletesZonas(1,1), BilletesZonas(0,0))
        var RESULT = 88.668875
        assertEquals(org.example.precioTotal(arrayPrueba),RESULT)

        arrayPrueba = arrayListOf(BilletesZonas(0,0), BilletesZonas(0,0))
        RESULT = 4.8
        assertEquals(org.example.precioTotal(arrayPrueba),RESULT)

        arrayPrueba = arrayListOf(BilletesZonas(4,0), BilletesZonas(0,0))
        RESULT = 42.4
        assertEquals(org.example.precioTotal(arrayPrueba),RESULT)

        // El resto de funciones no se puede hacer test porque no retornan nada o son un bucle que devuelven el input del usuario hasta que elija una opcion valida.
    }
}
