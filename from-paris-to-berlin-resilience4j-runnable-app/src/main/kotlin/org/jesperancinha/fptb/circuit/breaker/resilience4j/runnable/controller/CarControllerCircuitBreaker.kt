package org.jesperancinha.fptb.circuit.breaker.resilience4j.runnable.controller

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType.COUNT_BASED
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import io.github.resilience4j.kotlin.circuitbreaker.decorateSuspendFunction
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.jesperancinha.fptb.circuit.breaker.resilience4j.runnable.dto.Car
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.create
import java.time.Duration

/**
 * Created by jofisaes on 13/10/2021
 */
@RestController
@RequestMapping("/cars/circuit")
class CarControllerCircuitBreaker {

    private val logger = KotlinLogging.logger {}

    val circuitBreakerConfig =
        CircuitBreakerConfig.custom()
            .failureRateThreshold(20f)
            .slowCallRateThreshold(50f)
            .slowCallDurationThreshold(Duration.ofMillis(1000))
            .waitDurationInOpenState(Duration.ofMillis(1000))
            .maxWaitDurationInHalfOpenState(Duration.ofMillis(1000))
            .permittedNumberOfCallsInHalfOpenState(500)
            .minimumNumberOfCalls(2)
            .slidingWindowSize(2)
            .slidingWindowType(COUNT_BASED)
            .build()

    val circuitBreaker = CircuitBreakerRegistry.of(circuitBreakerConfig).circuitBreaker("TEST")

    private suspend fun getPublicCar(id: Long): Car {
        return circuitBreaker.decorateSuspendFunction {
            getPrivateCar(id)
        }.let { suspendFunction ->
            try {
                suspendFunction()
            } catch (exception: Exception) {
                logger.error { exception }
                Car("Opel Corsa")
            }
        }
    }

    private fun getPrivateCar(id: Long): Car {
        if (id == 2L) {
            throw RuntimeException()
        }
        return Car("Lancya")
    }

    @GetMapping("/{id}")
    private fun getCars(@PathVariable id: Long): Car {
        return runBlocking {
            getPublicCar(id)
        }
    }

    @GetMapping("/carros/{id}")
    private fun getCarros(@PathVariable id: Long): Car {
        return Car("Laborghini")
    }
}