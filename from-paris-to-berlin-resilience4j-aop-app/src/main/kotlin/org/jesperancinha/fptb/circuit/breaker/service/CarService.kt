package org.jesperancinha.fptb.circuit.breaker.service

import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.timelimiter.annotation.TimeLimiter
import org.jesperancinha.fptb.circuit.breaker.dto.Car
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.Duration


/**
 * Created by jofisaes on 13/10/2021
 */
@Component
open class CarService {

    @TimeLimiter(name = CARS)
    @CircuitBreaker(name = CARS, fallbackMethod = "launch")
    @Bulkhead(name = CARS)
    open fun getCar(): Mono<Car> {
        return Mono.just(Car("Fiat")).delayElement(Duration.ofSeconds(10));
    }

    private fun launch(ex: Exception): Mono<Car> {
        return Mono.just(Car("Jaguar"))
    }

    companion object {
        const val CARS = "cars"
    }
}