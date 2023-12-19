package org.example
import java.util.*

data class Datos (
    var billetesNombres:Array <String>,
    var billetesPrecios: Array<Float>,
    var zonas: Array<Int>,
    var zonasPrecios: Array<Float>
)
val datos = Datos(
    arrayOf("Billet senzill","TCasual","TUsual","TFamiliar","TJove"),
    arrayOf(2.4f,11.35f,40f,10f,80f),
    arrayOf(1,2,3),
    arrayOf(1f,1.3125f,1.8443f)
)

fun main() {
    println("Hello World!")
}
fun menus(scan: Scanner) {
    val billete = billetes(scan)
    if (billete != 4321) {
        val zona = zona(scan)
        println("Ha escollit la opcio:$billete, $zona")
    }
    else {
        println("Maquina aturada")

        do {
            println("Introdueix la clau secreta altra vegada per tonarla a posar en marcha")
            val linea = scan.nextLine()
        } while (linea != "4321")
    }
}
fun calcularPrecio(billete:Int,zona:Int,precioBilletes:Array<Float>,precioZona:Array<Float>):Float {
    var precio = 0f
    var numeroBillete = 1
    var found = false
    while (numeroBillete <= 5 && !found) {
        if (billete == numeroBillete) {
            precio = precioBilletes[numeroBillete-1]
            found = true
        }
        numeroBillete++
    }
    precio += precioZona[zona-1]
    return precio
}
fun billetes(scan:Scanner):Int {
    println("--------------------------------------------------------------------------\n" +
            "Quin billet desitja adquirir?\n" +
            "1 - Billet senzill\n" +
            "2 - TCasual\n" +
            "3 - TUsual\n" +
            "4 - TFamiliar\n" +
            "5 - TJove")
    var billete:Int
    do {
        billete = comprobarInt(scan, "Esa no es una opción valida")
    } while(billete in 1..4 || billete == 4321)

    return billete
}

fun zona(scan:Scanner):Int {
    println("Quina  zona vol viatjar?\n1\n2\n3")

    var zona:Int
    do {
        zona = comprobarInt(scan, "Esa no es una opción valida")
    } while(zona in 1..3)

    return zona
}
fun comprobarInt(scan:Scanner , err:String):Int {
    while (!scan.hasNextInt()) {
        scan.nextLine()
        println(err)
    }
    return scan.nextInt()

}