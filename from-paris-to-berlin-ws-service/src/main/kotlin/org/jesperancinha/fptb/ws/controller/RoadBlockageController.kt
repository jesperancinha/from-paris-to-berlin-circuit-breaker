package org.jesperancinha.fptb.ws.controller

import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.jesperancinha.fptb.ws.service.RoadBlockageWSService
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by jofisaes on 14/10/2021
 */
@RestController
@RequestMapping("/blockage")
class RoadBlockageController(
    private val roadBlockageService: RoadBlockageWSService,
) {
    @PostMapping(consumes = [APPLICATION_JSON_VALUE])
    suspend fun updateBlockage(@RequestBody location: Location) = roadBlockageService.setRoadBlock(location)
}