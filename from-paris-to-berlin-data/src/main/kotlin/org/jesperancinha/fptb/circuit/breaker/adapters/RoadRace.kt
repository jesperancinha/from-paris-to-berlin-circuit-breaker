package org.jesperancinha.fptb.circuit.breaker.adapters

import org.jesperancinha.fptb.circuit.breaker.domain.BlockageType
import org.jesperancinha.fptb.circuit.breaker.domain.Car
import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.jesperancinha.fptb.circuit.breaker.domain.RoadBlockTime
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.OffsetTime
import java.util.concurrent.TimeUnit
import kotlin.random.Random

/**
 * Created by jofisaes on 14/10/2021
 */
@Component
data class RoadRace(
    var cars: List<Car> = listOf(),
    var paris: Location = Location(),
) {
    private val timeTables = mutableMapOf<String, List<RoadBlockTime>>()
    fun init() {
        cars = (1..5).map {
            Car(
                id = it.toLong(),
                name = "name",
                model = "brand",
                location = paris,
                downtimeTSMS = LocalDateTime.now().toInstant(OffsetTime.now().offset).toEpochMilli(),
                downtimeTLMS = TimeUnit.MINUTES.toMillis(1),
                formerLocations = mutableListOf(paris)
            )
        }
        timeTables.clear()
        addTimeTables(paris)
    }

    private fun addTimeTables(location: Location) {
        location.apply {
            val roadBlockTime = timeTables[name]
            if (roadBlockTime == null) {
                blockageTimeTable.addAll((0..(Random.nextInt(0, 3) + 1))
                    .map { RoadBlockTime(Random.nextInt(10), BlockageType.values().random()) })
                timeTables[name] = blockageTimeTable
            }
            forward.forEach { addTimeTables(it) }
        }
    }

    fun randomMoveFw() {
        cars.filter { it.id != 5L }.forEach {
//            if (!it.isWaiting()) {
            it.location = it.location.forward.random()
            it.delay(Random.nextLong(1, 5))
            it.formerLocations.add(it.location)
//            }
        }
    }

}