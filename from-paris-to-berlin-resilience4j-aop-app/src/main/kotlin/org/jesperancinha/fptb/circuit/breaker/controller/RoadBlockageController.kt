package org.jesperancinha.fptb.circuit.breaker.controller

import org.jesperancinha.fptb.circuit.breaker.domain.RoadBlockagesMap
import org.jesperancinha.fptb.circuit.breaker.dto.Location
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

/**
 * Created by jofisaes on 14/10/2021
 */
@RestController
@RequestMapping("/blockage")
class RoadBlockageController(
    private val roadBlockagesMap: RoadBlockagesMap
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateBlockage(@RequestBody location: Location) {
        roadBlockagesMap.location= location
    }

    @GetMapping
    fun getCurrentBlockage(): Mono<Location>? {
        return Mono.just(roadBlockagesMap.location)
    }
}