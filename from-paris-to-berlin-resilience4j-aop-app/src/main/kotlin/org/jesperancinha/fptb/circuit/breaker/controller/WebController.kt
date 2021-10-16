package org.jesperancinha.fptb.circuit.breaker.controller

import org.jesperancinha.fptb.circuit.breaker.dto.RoadRaceDto
import org.jesperancinha.fptb.circuit.breaker.service.RoadBlockageService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

/**
 * Created by jofisaes on 16/10/2021
 */
@Controller
class WebController(
    private val roadBlockageService: RoadBlockageService,
) {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    fun greeting(message: String): RoadRaceDto {
        Thread.sleep(1000) // simulated delay
        return roadBlockageService.getCurrenRoadRace()
    }
}