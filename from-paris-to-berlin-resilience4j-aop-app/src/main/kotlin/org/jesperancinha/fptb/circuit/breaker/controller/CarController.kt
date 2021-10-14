package org.jesperancinha.fptb.circuit.breaker.controller

import org.jesperancinha.fptb.circuit.breaker.domain.RoadBlockagesMap
import org.jesperancinha.fptb.circuit.breaker.dto.Car
import org.jesperancinha.fptb.circuit.breaker.service.CarService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

/**
 * Created by jofisaes on 13/10/2021
 */
@RestController
@RequestMapping("/cars")
class CarController(
    private val carService: CarService,
    private val roadBlockagesMap: RoadBlockagesMap
) {

    @GetMapping("/{id}")
    private fun getCars(@PathVariable id: Int): Mono<Car> {
        return carService.getCar()
    }

    @GetMapping("/test/{id}")
    private fun getCarsTest(@PathVariable id: Int): Mono<Car> {
        return carService.getCar()
    }

    @GetMapping("/carros/{id}")
    private fun getCarros(@PathVariable id: Long): Mono<Car> {
        return Mono.just(Car("Lamborghini"))
    }

    private fun fallback(ex: Throwable): Mono<Car> {
        return Mono.just(Car("Rolls Royce"))
    }
}