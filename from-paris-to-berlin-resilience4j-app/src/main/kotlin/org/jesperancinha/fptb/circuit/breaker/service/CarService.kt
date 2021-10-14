package org.jesperancinha.fptb.circuit.breaker.service

import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerEvent
import io.github.resilience4j.core.registry.EntryAddedEvent
import io.github.resilience4j.core.registry.EntryRemovedEvent
import io.github.resilience4j.core.registry.EntryReplacedEvent
import io.github.resilience4j.core.registry.RegistryEventConsumer
import io.github.resilience4j.retry.Retry
import io.github.resilience4j.retry.event.RetryEvent
import io.github.resilience4j.timelimiter.annotation.TimeLimiter
import org.jesperancinha.fptb.circuit.breaker.dto.Car
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.time.Duration

const val CARS = "backendA"

/**
 * Created by jofisaes on 13/10/2021
 */
@Component
class CarService {

    @TimeLimiter(name = CARS)
    @CircuitBreaker(name = CARS, fallbackMethod = "launch")
    @Bulkhead(name = CARS)
    open fun getCar(): Mono<Car> {
        return Mono.just(Car("Fiat")).delayElement(Duration.ofSeconds(10));
    }

    private fun launch(ex: Exception): Mono<Car> {
        return Mono.just(Car("Jaguar"))
    }


}