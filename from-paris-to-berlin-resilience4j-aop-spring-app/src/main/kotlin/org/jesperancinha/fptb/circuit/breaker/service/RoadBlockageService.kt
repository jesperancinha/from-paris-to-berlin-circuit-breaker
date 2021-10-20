package org.jesperancinha.fptb.circuit.breaker.service

import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.timelimiter.annotation.TimeLimiter
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import mu.KotlinLogging
import org.jesperancinha.fptb.circuit.breaker.adapters.RoadRace
import org.jesperancinha.fptb.circuit.breaker.adapters.getMyCar
import org.jesperancinha.fptb.circuit.breaker.domain.BlockageType
import org.jesperancinha.fptb.circuit.breaker.domain.Car
import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.jesperancinha.fptb.circuit.breaker.domain.isWaiting
import org.jesperancinha.fptb.circuit.breaker.domain.randomFw
import org.jesperancinha.fptb.circuit.breaker.domain.toDto
import org.jesperancinha.fptb.circuit.breaker.dto.RoadRaceDto
import org.jesperancinha.fptb.circuit.breaker.exception.BlockageException
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException
import kotlin.concurrent.schedule

private val RoadRace?.toDto: RoadRaceDto
    get() = this?.cars?.let {
        return RoadRaceDto(it.map { car ->
            car.toDto()
        }, paris, errorReports)
    } ?: RoadRaceDto(listOf(), Location(), mutableListOf())

/**
 * Created by jofisaes on 15/10/2021
 */
@Service
open class RoadBlockageService(
    open val template: SimpMessagingTemplate,
    open val roadRace: RoadRace,
) {

    init {
        Timer().schedule(TimeUnit.SECONDS.toMillis(1), TimeUnit.SECONDS.toMillis(1)) {
            moveCars()
        }
    }

    private val logger = KotlinLogging.logger {}

    suspend fun setRoadBlock(location: Location) {
        roadRace.paris = location
        startGame()
        fireResponse()
    }

    fun fireResponse() {
        template.convertAndSend("/topic/game", roadRace.toDto)
    }

    fun getStartLocation(): Location = roadRace.paris

    private suspend fun startGame() {
        coroutineScope {
            launch {
                roadRace.init()
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

    @TimeLimiter(name = CarService.CARS, fallbackMethod = "reportTimeout")
    @CircuitBreaker(name = CarService.CARS, fallbackMethod = "reportError")
    @Bulkhead(name = CarService.CARS)
    open fun moveToCity(id: Long): Mono<RoadRace> {
        val myCar = roadRace.getMyCar()
        if (!myCar.isWaiting()) {
            val destination = myCar.location.forward.find { it.id == id }
            val blockage = destination?.blockageTimeTable?.find {
                it.minute.toString().last() == LocalDateTime.now().minute.toString().last()
            }
            blockage?.let { roadBlockTime ->
                when (roadBlockTime.blockageType) {
                    BlockageType.TIMEOUT -> return Mono.just(roadRace).delayElement(Duration.ofSeconds(10))
                    BlockageType.ERROR -> return Mono.create { it.error(BlockageException()) }
                    BlockageType.UNKNOWN -> return listOf(Mono.create { it.error(BlockageException()) },
                        Mono.just(roadRace).delayElement(Duration.ofSeconds(10))).random()
                    else -> print("Nothing to do here!")
                }
            }

            destination?.let {
                myCar.delay(10)
                myCar.location = it
                myCar.formerLocations.add(myCar.location)
            }
        }
        return Mono.just(roadRace)
    }

    private fun reportError(exception: Exception): Mono<RoadRace> {
        logger.info("---- **** error reported")
        roadRace.getMyCar().delay(20L)
        roadRace.getMyCar().randomFw()
        roadRace.errorReports.add("Error reported! at ${LocalDateTime.now()}")
        return Mono.create { it.error(BlockageException()) }
    }

    private fun reportTimeout(exception: TimeoutException): Mono<RoadRace> {
        logger.info("---- **** timeout reported!")
        roadRace.getMyCar().delay(50L)
        roadRace.getMyCar().randomFw()
        roadRace.errorReports.add("Timeout reported! at ${LocalDateTime.now()}")
        return Mono.just(roadRace)
    }
}
