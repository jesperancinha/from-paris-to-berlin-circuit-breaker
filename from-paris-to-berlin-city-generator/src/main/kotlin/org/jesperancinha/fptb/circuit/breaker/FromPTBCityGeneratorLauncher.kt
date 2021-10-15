package org.jesperancinha.fptb.circuit.breaker

import com.fasterxml.jackson.databind.ObjectMapper
import org.jesperancinha.fptb.circuit.breaker.domain.Location

fun main(args: Array<String>) {
    val berlin = Location(8, "Berlin", listOf())

    val aken = Location(5, "Aken", listOf(berlin))
    val heerlen = Location(6, "Heerlen", listOf(berlin))
    val duren = Location(7, "Düren", listOf(berlin))


    val soissons = Location(2, "Soissons", listOf(aken, heerlen, duren))
    val compiegne = Location(3, "Compiègne", listOf(aken, heerlen, duren))
    val reims = Location(4, "Reims", listOf(aken, heerlen, duren))

    val paris = Location(1, "Paris", listOf(soissons, compiegne, reims))

    val objectMapper = ObjectMapper()

    print(objectMapper.writeValueAsString(paris))
}

