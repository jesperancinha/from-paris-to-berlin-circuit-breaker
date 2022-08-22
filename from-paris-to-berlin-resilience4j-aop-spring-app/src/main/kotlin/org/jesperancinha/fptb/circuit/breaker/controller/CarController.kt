package org.jesperancinha.fptb.circuit.breaker.controller

import org.jesperancinha.fptb.circuit.breaker.domain.Car
import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.jesperancinha.fptb.circuit.breaker.service.CarService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
) {
    @GetMapping("/{id}")
    private fun getCars(@PathVariable id: Int): Mono<Car> =  carService.getCar()

    @GetMapping("/test/{id}")
    private fun getCarsTest(@PathVariable id: Int): Mono<Car> = carService.getCar()

    @GetMapping("/carros/{id}")
    private fun getCarros(@PathVariable id: Long): Mono<Car> = Mono.just(Car(3, "The boss bling bling man", "Lamborghini", Location()))
}