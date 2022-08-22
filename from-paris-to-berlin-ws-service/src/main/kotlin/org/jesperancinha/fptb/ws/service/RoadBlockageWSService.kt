package org.jesperancinha.fptb.ws.service


import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import mu.KotlinLogging
import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.jesperancinha.fptb.circuit.breaker.domain.toDto
import org.jesperancinha.fptb.circuit.breaker.dto.RoadRaceDto
import org.jesperancinha.fptb.circuit.breaker.services.RoadRaceCacheService
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule

private val RoadRaceCacheService?.toDto: RoadRaceDto
    get() = this?.cars?.let {
        return RoadRaceDto(it.map { car ->
            car.toDto()
        }, paris, errorReports)
    } ?: RoadRaceDto(listOf(), Location(), mutableListOf())

/**
 * Created by jofisaes on 15/10/2021
 */
@Service
open class RoadBlockageWSService(
    open val template: SimpMessagingTemplate,
    open val roadRaceCacheService: RoadRaceCacheService,
) {

    init {
        Timer().schedule(TimeUnit.SECONDS.toMillis(1), TimeUnit.SECONDS.toMillis(1)) {
            moveCars()
        }
    }

    suspend fun setRoadBlock(location: Location) {
        roadRaceCacheService.paris = location
        startGame()
        fireResponse()
    }


    private suspend fun startGame() {
        coroutineScope {
            launch {
                roadRaceCacheService.init()
            }
        }
    }


    fun fireResponse() {
        template.convertAndSend("/topic/game", roadRaceCacheService.toDto)
    }

    fun getCurrenRoadRace(): RoadRaceDto = roadRaceCacheService.toDto

    private fun moveCars() {
        roadRaceCacheService.randomMoveFw()
        fireResponse()
    }

}
