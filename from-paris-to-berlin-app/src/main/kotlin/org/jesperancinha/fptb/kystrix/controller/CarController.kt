package org.jesperancinha.fptb.kystrix.controller

import org.jesperancinha.fptb.kystrix.dto.Car
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
class CarController {

    @GetMapping("/{id}")
    private fun getCars(@PathVariable id: String): Mono<Car> {
        return Mono.create { Car("Jaguar") }
    }
}