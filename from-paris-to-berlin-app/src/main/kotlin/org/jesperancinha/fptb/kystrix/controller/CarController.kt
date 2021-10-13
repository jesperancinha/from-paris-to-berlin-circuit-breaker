package org.jesperancinha.fptb.kystrix.controller

import org.jesperancinha.fptb.kystrix.dto.Car
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.create
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import se.haleby.kystrix.hystrixObservableCommand
import se.haleby.kystrix.monoCommand
import se.haleby.kystrix.toMono

/**
 * Created by jofisaes on 13/10/2021
 */
@RestController
@RequestMapping("/cars")
class CarController {

    val webClient: WebClient = create("http://localhost:8080")

    @GetMapping("/{id}")
    private fun getCars(@PathVariable id: Int): Mono<Car> {
        return if (id == 1)
            Mono.just(Car("Jaguar")) else {
            hystrixObservableCommand<Car> {
                groupKey("Test")
                commandKey("Test-Command")
                monoCommand {
                    webClient.get().uri("/cars/carros/1").retrieve().bodyToMono()
                }
                commandProperties {
                    withExecutionTimeoutInMilliseconds(10000)
                    withFallbackEnabled(false)
                }
            }.toMono()
        }
    }

    @GetMapping("/carros/{id}")
    private fun getCarros(@PathVariable id: Long): Mono<Car> {
        return Mono.just(Car("Laborghini"))
    }
}