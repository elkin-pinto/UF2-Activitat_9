package org.example
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

data class Datos (
    var billetesNombres:Array <String>,
    var billetesPrecios: Array<Float>,
    var zonas: Array<Int>,
    var zonasPrecios: Array<Float>,
    var efectivoAceptado: Array<Float>
)

data class BilletesZonas (
    var billete:Int,
    var zona:Int
)

val datos = Datos(
    arrayOf("Billet senzill","TCasual","TUsual","TFamiliar","TJove"),
    arrayOf(2.4f,11.35f,40f,10f,80f),
    arrayOf(1,2,3),
    arrayOf(1f,1.3125f,1.8443f),
    arrayOf(0.05f,0.1f,0.2f,0.5f,1f,2f,5f,10f,20f,50f)
)

fun main() {
    val scan = Scanner(System.`in`)
    while (true) {
        val total = ArrayList<BilletesZonas> ()
        do {
            val seguir = menus(scan,total)
        } while (seguir)
        pago(scan,total)
        tiquet(scan, total)
    }
}
fun menus(scan: Scanner, total:ArrayList<BilletesZonas>):Boolean {
    val billete = billetes(scan)
    if (billete != 4321) {
        val zona = zona(scan)
        total += BilletesZonas(billete,zona)
        println("Ha escollit la opcio:${datos.billetesNombres[billete]}, zona ${datos.zonas[zona]}")
        println("El preu del billet es ${String.format("%.2f",calcularPrecio(BilletesZonas(billete,zona)))}")
        println("Vols seguir comprant?[S/N]")
        var input:String
        do {
            input = scan.next()
        }while (input != "s" && input != "S" && input != "n" && input != "N")

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

/*
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

*/

fun calcularPrecio(compra:BilletesZonas):Float {

    val precio:Float = datos.billetesPrecios[compra.billete] * datos.zonasPrecios[compra.zona]
    return precio
}

fun billetes(scan:Scanner):Int {
    println("- - - - - - - - - - - - - - - - - - - -\n" +
            "Quin billet desitja adquirir?")
    for (i in datos.billetesNombres.indices) println("${i + 1} - ${datos.billetesNombres[i]}")
    var billete:Int
    do {
        billete = comprobarInt(scan, "Esa no es una opción valida") - 1
    } while(billete !in 0 until datos.billetesNombres.size && billete != 4321)

    return billete
}

fun zona(scan:Scanner):Int {
    println("Quina  zona vol viatjar?")
    for (i in datos.zonas) println(i)

    var zona:Int
    do {
        zona = comprobarInt(scan, "Esa no es una opción valida")
    } while(zona !in datos.zonas)

    return zona - 1
}
fun comprobarInt(scan:Scanner , err:String):Int {
    while (!scan.hasNextInt()) {
        scan.nextLine()
        println(err)
    }
    return scan.nextInt()

}

fun precioTotal(total:ArrayList<BilletesZonas>):Float {
    var precio = 0f
    for (i in total) {
        precio += datos.billetesPrecios[i.billete] * datos.zonasPrecios[i.zona]
    }
    return precio
}
fun pago(scan: Scanner,total:ArrayList<BilletesZonas>) {
    var restante = redondearFloat(precioTotal(total))
    scan.nextLine()

    println("Ha comprat ${total.size} billetes, ha de pagar ${String.format("%.2f",restante)}€")
    while (restante > 0f) {
        var efectivoIntroducido = 0f
        println("Introdueixi monedes o bitllets vàlids de EURO?")
        if (scan.hasNextFloat()) efectivoIntroducido = scan.nextFloat()
        else scan.nextLine()
        if (efectivoIntroducido !in datos.efectivoAceptado) efectivoIntroducido = 0f
        restante -= efectivoIntroducido
        if (restante > 0f) println("Ha introduit: ${String.format("%.2f",efectivoIntroducido)}€, li resta por pagar ${String.format("%.2f",restante)}€")
        else println("Reculli el seu billet i el se canvi: ${String.format("%.2f",restante * -1f)}€")
    }
}

fun redondearFloat(float:Float):Float {
    return (float * 100).roundToInt() / 100f
}
fun tiquet(scan: Scanner,total:ArrayList<BilletesZonas>) {
    println("Vols el tiquet[S,N]")
    var input:String
    do {
        input = scan.next()
    }while (input != "s" && input != "S" && input != "n" && input != "N")

    when (input) {
        "s","S" -> {
            println("_____TIQUET_____")
            for (i in total) println("${datos.billetesNombres[i.billete]} zona ${datos.zonas[i.zona]} - Preu: ${String.format("%.2f", calcularPrecio(i))}")
            println("---------------------\n" +
                    "Reculli el teu tiquet.\n" +
                    "Adeu!!")
        }
    }
}