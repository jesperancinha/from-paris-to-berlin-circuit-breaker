package org.jesperancinha.fptb.circuit.breaker.service

import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

const val CARS_2 = "cars/2"

/**
 * Created by jofisaes on 13/10/2021
 */
@Component
class CarService {

    @CircuitBreaker(name = CARS_2)
    @Bulkhead(name = CARS_2)
    @Retry(name = CARS_2)
    open fun monoSuccess(): Mono<String?>? {
        return Mono.just("Fiat")
    }

}