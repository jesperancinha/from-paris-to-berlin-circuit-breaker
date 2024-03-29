package org.jesperancinha.fptb.circuit.breaker

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.fptb.circuit.breaker.domain.Location

fun main() {
    val berlin = Location(10, "Berlin", listOf())

    val wolfsburg = Location(8, "Wolfsburg", listOf(berlin))
    val braunschweig = Location(9, "Braunschweig", listOf(berlin))


    val aken = Location(5, "Aken", listOf(wolfsburg, braunschweig))
    val heerlen = Location(6, "Heerlen", listOf(wolfsburg, braunschweig))
    val duren = Location(7, "Düren", listOf( ))


    val soissons = Location(2, "Soissons", listOf(aken, heerlen, duren))
    val compiegne = Location(3, "Compiègne", listOf(aken, heerlen, duren))
    val reims = Location(4, "Reims", listOf(aken, heerlen, duren))

    val paris = Location(1, "Paris", listOf(soissons, compiegne, reims))

    val objectMapper = ObjectMapper()

    print(objectMapper.writeValueAsString(paris))
}

