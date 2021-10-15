package org.jesperancinha.fptb.circuit.breaker.adapters

import org.jesperancinha.fptb.circuit.breaker.domain.BlockageType
import org.jesperancinha.fptb.circuit.breaker.domain.Car
import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.jesperancinha.fptb.circuit.breaker.domain.RoadBlockTime
import org.springframework.stereotype.Component
import kotlin.random.Random

/**
 * Created by jofisaes on 14/10/2021
 */
@Component
data class RoadRace(
    var cars: List<Car> = listOf(),
    var paris: Location = Location(),
) {
    fun init() {
        cars = (1..10).map { Car(it.toLong(), "name", "brand", paris) }
        addTimeTables(paris)
    }

    private fun addTimeTables(location: Location) {
        location.apply {
            blockageTimeTable.clear()
            blockageTimeTable.addAll((0..(Random.nextInt(0, 2) + 1))
                .map { RoadBlockTime(Random.nextInt(60), BlockageType.values().random()) })
            forward.forEach { addTimeTables(it) }
        }
    }
}