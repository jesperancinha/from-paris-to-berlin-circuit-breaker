package org.jesperancinha.fptb.circuit.breaker.adapters

import org.jesperancinha.fptb.circuit.breaker.domain.Car
import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.springframework.stereotype.Component

/**
 * Created by jofisaes on 14/10/2021
 */
@Component
data class RoadRace(
    var cars: List<Car> = listOf(),
    var location: Location = Location(),
) {
    fun init(){

    }
}