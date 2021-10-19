package org.jesperancinha.fptb.circuit.breaker.resilience4j.runnable.controller

import org.jesperancinha.fptb.circuit.breaker.resilience4j.runnable.dto.Car
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.create
import reactor.core.publisher.Mono

/**
 * Created by jofisaes on 13/10/2021
 */
@RestController
@RequestMapping("/cars/custom")
class CarControllerCustom {

    private val webClient: WebClient = create("http://localhost:8080")

    @GetMapping("/{id}")
    private fun getCars(@PathVariable id: Int): Car {
        return Car("Jaguar")
    }

    @GetMapping("/carros/{id}")
    private fun getCarros(@PathVariable id: Long): Car {
        return Car("Laborghini")
    }
}