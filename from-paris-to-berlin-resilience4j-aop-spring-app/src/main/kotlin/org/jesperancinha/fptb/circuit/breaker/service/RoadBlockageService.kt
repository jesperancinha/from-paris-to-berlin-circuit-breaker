package org.jesperancinha.fptb.circuit.breaker.service

import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.timelimiter.annotation.TimeLimiter
import mu.KotlinLogging
import org.jesperancinha.fptb.circuit.breaker.domain.BlockageType.*
import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.jesperancinha.fptb.circuit.breaker.domain.isWaiting
import org.jesperancinha.fptb.circuit.breaker.domain.randomFw
import org.jesperancinha.fptb.circuit.breaker.domain.toDto
import org.jesperancinha.fptb.circuit.breaker.dto.RoadRaceDto
import org.jesperancinha.fptb.circuit.breaker.exception.BlockageException
import org.jesperancinha.fptb.circuit.breaker.services.RoadRaceCacheService
import org.jesperancinha.fptb.circuit.breaker.services.getMyCar
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import java.time.Duration
import java.time.LocalDateTime
import java.util.concurrent.TimeoutException

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
class RoadBlockageService(
    open val roadRaceCacheService: RoadRaceCacheService,
    @Value("\${fptb.ws-service}")
    val wsServiceUrl: String
) {

    val webClient: WebClient = WebClient.create()

    private val logger = KotlinLogging.logger {}

    fun getStartLocation(): Location = roadRaceCacheService.paris

    fun getCurrenRoadRace(): RoadRaceDto {
        return roadRaceCacheService.toDto
    }

    @TimeLimiter(name = CarService.CARS, fallbackMethod = "reportTimeout")
    @CircuitBreaker(name = CarService.CARS, fallbackMethod = "reportError")
    @Bulkhead(name = CarService.CARS)
    fun moveToCity(id: Long): Mono<RoadRaceCacheService> {
        val myCar = roadRaceCacheService.getMyCar()
        if (!myCar.isWaiting()) {
            val destination = myCar.location.forward.find { it.id == id }
            val blockage = destination?.blockageTimeTable?.find {
                it.minute.toString().last() == LocalDateTime.now().minute.toString().last()
            }
            blockage?.let { roadBlockTime ->
                when (roadBlockTime.blockageType) {
                    TIMEOUT -> return Mono.just(roadRaceCacheService).delayElement(Duration.ofSeconds(10))
                    ERROR -> return Mono.error(BlockageException())
                    UNKNOWN -> return listOf(
                        Mono.error(BlockageException()),
                        Mono.just(roadRaceCacheService).delayElement(Duration.ofSeconds(10))
                    ).random()

                    else -> print("Nothing to do here!")
                }
            }

            destination?.let { locationIt ->
                val cars = roadRaceCacheService.cars
                cars.first { it.id == myCar.id }.apply {
                    delay(10)
                    location = locationIt
                    formerLocations.add(locationIt)
                }
                roadRaceCacheService.cars = cars
            }
        }
        return Mono.just(roadRaceCacheService)
    }

    @SuppressWarnings("unused")
    private fun reportError(exception: Exception): Mono<RoadRaceCacheService> {
        logger.info("---- **** error reported")
        roadRaceCacheService.getMyCar().delay(20L)
        val myCar = roadRaceCacheService.getMyCar()
        myCar.randomFw()
        val cars = roadRaceCacheService.cars
        cars.removeIf { it.id == myCar.id }
        cars.add(myCar)
        roadRaceCacheService.cars = cars
        val errorReports = roadRaceCacheService.errorReports
        errorReports.add("Error reported! at ${LocalDateTime.now()}")
        roadRaceCacheService.errorReports = errorReports
        return Mono.error(BlockageException())
    }

    @SuppressWarnings("unused")
    private fun reportTimeout(exception: TimeoutException): Mono<RoadRaceCacheService> {
        logger.info("---- **** timeout reported!")
        roadRaceCacheService.getMyCar().delay(50L)
        val myCar = roadRaceCacheService.getMyCar()
        myCar.randomFw()
        val cars = roadRaceCacheService.cars
        cars.removeIf { it.id == myCar.id }
        cars.add(myCar)
        roadRaceCacheService.cars = cars
        val errorReports = roadRaceCacheService.errorReports
        errorReports.add("Timeout reported! at ${LocalDateTime.now()}")
        roadRaceCacheService.errorReports = errorReports
        return Mono.just(roadRaceCacheService)
    }

    fun setRoadBlock(location: Location) = webClient.post()
        .uri(wsServiceUrl)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .body(Mono.just(location), Location::class.java)
        .retrieve()
        .bodyToMono<Location>()
}
