package org.jesperancinha.fptb.circuit.breaker

import org.jesperancinha.fptb.circuit.breaker.dto.BlockageType
import org.jesperancinha.fptb.circuit.breaker.dto.Location

fun main(args: Array<String>) {
    println("Hello, World")
    val paris = Location(1, BlockageType.ERROR, "Paris", listOf())
    val villersCotterets = Location(2, BlockageType.ERROR, "Villers-Cotterêts", listOf())
    val soissons = Location(2, BlockageType.ERROR, "Soissons", listOf())
}

