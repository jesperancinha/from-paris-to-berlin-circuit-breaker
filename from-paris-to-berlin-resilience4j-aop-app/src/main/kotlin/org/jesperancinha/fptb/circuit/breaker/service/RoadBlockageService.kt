package org.jesperancinha.fptb.circuit.breaker.service

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.jesperancinha.fptb.circuit.breaker.adapters.RoadRace
import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.jesperancinha.fptb.circuit.breaker.domain.toDto
import org.jesperancinha.fptb.circuit.breaker.dto.RoadRaceDto
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule

private val RoadRace.toDto: RoadRaceDto
    get() {
        val cars = this.cars.map { car ->
            car.toDto()
        }
        return RoadRaceDto(cars, paris)
    }

/**
 * Created by jofisaes on 15/10/2021
 */
@Service
class RoadBlockageService(
    private val roadRace: RoadRace,
    private val template: SimpMessagingTemplate,
) {

    suspend fun setRoadBlock(location: Location) {
        roadRace.paris = location
        startGame()
        fireResponse()
    }

    fun fireResponse() {
        template.convertAndSend("/topic/game", roadRace)
    }

    fun getStartLocation(): Location = roadRace.paris

    private suspend fun startGame() {
        coroutineScope {
            launch {
                roadRace.init();
                val schedule = Timer().schedule(TimeUnit.SECONDS.toMillis(10), TimeUnit.SECONDS.toMillis(10)) {
                    moveCars()
                }
//                schedule.cancel()
            }
        }
    }

    private fun moveCars() {
        roadRace.randomMoveFw()
        fireResponse()
    }

    fun getCurrenRoadRace(): RoadRaceDto {
        return roadRace.toDto
    }
}