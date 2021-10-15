package org.jesperancinha.fptb.circuit.breaker.service

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.jesperancinha.fptb.circuit.breaker.adapters.RoadRace
import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.jesperancinha.fptb.circuit.breaker.dto.CarDto
import org.jesperancinha.fptb.circuit.breaker.dto.LocationDto
import org.jesperancinha.fptb.circuit.breaker.dto.RoadRaceDto
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule

private val RoadRace.toDto: RoadRaceDto
    get() {
        val cars = this.cars.map { car ->
            CarDto(car.id, car.name, car.model, LocationDto(
                car.location.id,
                car.location.name,
                car.location.forward.map { location ->
                    LocationDto(location.id, location.name, listOf(), location.blockageTimeTable)
                },
                car.location.blockageTimeTable))
        }
        return RoadRaceDto(cars, paris)
    }

/**
 * Created by jofisaes on 15/10/2021
 */
@Service
class RoadBlockageService(
    private val roadRace: RoadRace,
) {
    suspend fun setRoadBlock(location: Location) {
        roadRace.paris = location
        startGame()
    }

    fun getStartLocation(): Location = roadRace.paris

    private suspend fun startGame() {
        coroutineScope {
            launch {
                roadRace.init();
                val schedule = Timer().schedule(10, TimeUnit.SECONDS.toMillis(10)) {
                    moveCars()
                }
//                schedule.cancel()
            }
        }
    }

    private fun moveCars() {
        roadRace.randomMoveFw()
    }

    fun getCurrenRoadRace(): RoadRaceDto {
        return roadRace.toDto
    }
}