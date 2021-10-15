package org.jesperancinha.fptb.circuit.breaker.controller

import org.jesperancinha.fptb.circuit.breaker.adapters.RoadRace
import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.jesperancinha.fptb.circuit.breaker.dto.RoadRaceDto
import org.jesperancinha.fptb.circuit.breaker.service.RoadBlockageService
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
    private val roadBlockageService: RoadBlockageService,
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun updateBlockage(@RequestBody location: Location) = roadBlockageService.setRoadBlock(location)


    @GetMapping
    fun getCurrentBlockage(): Mono<Location?> = Mono.just(roadBlockageService.getStartLocation())


    @GetMapping("/roadRace")
    fun getCurrentRoadRace(): Mono<RoadRaceDto?> = Mono.just(roadBlockageService.getCurrenRoadRace())

}