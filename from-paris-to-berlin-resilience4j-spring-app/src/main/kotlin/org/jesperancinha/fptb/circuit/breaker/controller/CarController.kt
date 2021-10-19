package org.jesperancinha.fptb.circuit.breaker.controller

import io.github.resilience4j.bulkhead.BulkheadRegistry
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import io.github.resilience4j.reactor.bulkhead.operator.BulkheadOperator
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator
import io.github.resilience4j.reactor.timelimiter.TimeLimiterOperator
import io.github.resilience4j.timelimiter.TimeLimiter
import io.github.resilience4j.timelimiter.TimeLimiterRegistry
import org.jesperancinha.fptb.circuit.breaker.dto.Car
import org.jesperancinha.fptb.circuit.breaker.service.CARS
import org.jesperancinha.fptb.circuit.breaker.service.CarService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.concurrent.TimeoutException

/**
 * Created by jofisaes on 13/10/2021
 */
@RestController
@RequestMapping("/cars")
class CarController(
    private val carService: CarService,
    timeLimiterRegistry: TimeLimiterRegistry,
    circuitBreakerRegistry: CircuitBreakerRegistry,
    bulkheadRegistry: BulkheadRegistry
) {
    private var timeLimiter: TimeLimiter = timeLimiterRegistry.timeLimiter(CARS)
    private var circuitBreaker = circuitBreakerRegistry.circuitBreaker(CARS)
    private var bulkhead = bulkheadRegistry.bulkhead(CARS)

    @GetMapping("/{id}")
    private fun getCars(@PathVariable id: Int): Mono<Car> {
        return carService.getCar()
            .transform(TimeLimiterOperator.of(timeLimiter))
            .transform(CircuitBreakerOperator.of(circuitBreaker))
            .transform(BulkheadOperator.of(bulkhead))
            .onErrorResume(TimeoutException::class.java, ::fallback)
    }


    @GetMapping("/test/{id}")
    private fun getCarsTest(@PathVariable id: Int): Mono<Car> {
        return carService.getCar()
            .transform(TimeLimiterOperator.of(timeLimiter))
            .transform(CircuitBreakerOperator.of(circuitBreaker))
            .transform(BulkheadOperator.of(bulkhead))
            .onErrorResume(TimeoutException::class.java, ::fallback)
    }

    @GetMapping("/carros/{id}")
    private fun getCarros(@PathVariable id: Long): Mono<Car> {
        return Mono.just(Car("Laborghini"))
    }

    private fun fallback(ex: Throwable): Mono<Car> {
        return Mono.just(Car("Rolls Royce"))
    }
}