package org.jesperancinha.fptb.circuit.breaker.controller

import org.jesperancinha.fptb.circuit.breaker.dto.Car
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.create
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import rx.Observable
import se.haleby.kystrix.hystrixObservableCommand
import se.haleby.kystrix.monoCommand
import se.haleby.kystrix.toMono
import java.time.Duration

/**
 * Created by jofisaes on 13/10/2021
 */
@RestController
@RequestMapping("/cars")
class CarController {

    private val webClient: WebClient = create("http://localhost:8080")

    @GetMapping("/{id}")
    private fun getCars(@PathVariable id: Int): Mono<Car> {
        return if (id == 1)
            Mono.just(Car("Jaguar")) else {
            hystrixObservableCommand<Car> {
                groupKey("Test2")
                commandKey("Test-Command2")
                monoCommand {
                    webClient.get().uri("/cars/carros/1").retrieve().bodyToMono<Car>()
                        .delayElement(Duration.ofSeconds(1))
                }
                commandProperties {
                    withRequestLogEnabled(true)
                    withExecutionTimeoutInMilliseconds(5000)
                    withExecutionTimeoutEnabled(true)
                    withFallbackEnabled(true)
                    withCircuitBreakerEnabled(false)
                    withCircuitBreakerForceClosed(true)
                }
                fallback { Observable.just(Car("Tank1")) }
            }.toMono()
        }
    }

    @GetMapping("/timeout/{id}")
    private fun getCarsTimeout(@PathVariable id: Int): Mono<Car> {
        return if (id == 1)
            Mono.just(Car("Jaguar")) else {
            hystrixObservableCommand<Car> {
                groupKey("Test3")
                commandKey("Test-Command3")
                monoCommand {
                    webClient.get().uri("/cars/carros/1").retrieve().bodyToMono<Car>()
                        .delayElement(Duration.ofSeconds(1))
                }
                commandProperties {
                    withRequestLogEnabled(true)
                    withExecutionIsolationThreadInterruptOnTimeout(true)
                    withExecutionTimeoutInMilliseconds(500)
                    withExecutionTimeoutEnabled(true)
                    withFallbackEnabled(true)
                    withCircuitBreakerEnabled(false)
                    withCircuitBreakerForceClosed(true)
                }
                fallback { Observable.just(Car("Tank2")) }
            }.toMono()
        }
    }

    @GetMapping("/carros/{id}")
    private fun getCarros(@PathVariable id: Long): Mono<Car> {
        return Mono.just(Car("Laborghini"))
    }
}