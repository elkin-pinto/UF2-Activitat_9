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
    val scan = Scanner(System.`in`)
    var total = 0
    do {
        val seguir = menus(scan)
    } while (seguir)

}
fun menus(scan: Scanner):Boolean {
    val billete = billetes(scan)
    if (billete != 4321) {
        val zona = zona(scan)
        println("Ha escollit la opcio:$billete, $zona")
        println("Vols el tiquet[S/N]")
        var input:String
        do {
            input = scan.next()
        }while (input == "s" || input == "S" || input == "n" || input == "N")

        when (input) {
            "s","S" -> return true
            "n","N" -> return false
        }
    }
    else {
        println("Maquina aturada")

        do {
            println("Introdueix la clau secreta altra vegada per tonarla a posar en marcha")
            val linea = scan.nextLine()
        } while (linea != "4321")
    }
    return true
}

fun  calcularPrecio() {

}
fun billetes(scan:Scanner):Int {
    println("--------------------------------------------------------------------------\n" +
            "Quin billet desitja adquirir?")
    for (i in datos.billetesNombres.indices) println("${i + 1} - ${datos.billetesNombres[i]}")
    var billete:Int
    do {
        billete = comprobarInt(scan, "Esa no es una opción valida") - 1
    } while(billete in 1 until datos.billetesNombres.size || billete == 4321)

    return billete
}

fun zona(scan:Scanner):Int {
    println("Quina  zona vol viatjar?")
    for (i in datos.zonas) println(i)

    var zona:Int
    do {
        zona = comprobarInt(scan, "Esa no es una opción valida") - 1
    } while(zona in datos.zonas)

    return zona
}
fun comprobarInt(scan:Scanner , err:String):Int {
    while (!scan.hasNextInt()) {
        scan.nextLine()
        println(err)
    }
    return scan.nextInt()

}