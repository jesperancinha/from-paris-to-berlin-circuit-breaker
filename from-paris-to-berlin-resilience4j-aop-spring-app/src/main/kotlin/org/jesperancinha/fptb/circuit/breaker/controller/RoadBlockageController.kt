package org.jesperancinha.fptb.circuit.breaker.controller

import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.jesperancinha.fptb.circuit.breaker.dto.RoadRaceDto
import org.jesperancinha.fptb.circuit.breaker.service.RoadBlockageService
import org.jesperancinha.fptb.circuit.breaker.services.RoadRaceCacheService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
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
    fun updateBlockage(@RequestBody location: Location) = roadBlockageService.setRoadBlock(location)

    @GetMapping
    fun getCurrentBlockage(): Mono<Location> = Mono.just(roadBlockageService.getStartLocation())

    @GetMapping("/roadRace")
    fun getCurrentRoadRace(): Mono<RoadRaceDto> = Mono.just(roadBlockageService.getCurrenRoadRace())

    @GetMapping("/moveToCity/{cityId}")
    fun moveToCity(@PathVariable cityId: Long): Mono<RoadRaceCacheService> = roadBlockageService.moveToCity(cityId)
}