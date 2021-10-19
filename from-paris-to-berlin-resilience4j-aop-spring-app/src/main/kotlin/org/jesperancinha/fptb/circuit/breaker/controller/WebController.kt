package org.jesperancinha.fptb.circuit.breaker.controller

import org.jesperancinha.fptb.circuit.breaker.dto.RoadRaceDto
import org.jesperancinha.fptb.circuit.breaker.service.RoadBlockageService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import java.time.LocalDateTime

/**
 * Created by jofisaes on 16/10/2021
 */
@Controller
class WebController(
    private val roadBlockageService: RoadBlockageService,
) {

    @MessageMapping("/hello")
    @SendTo("/topic/game")
    fun game(message: String): RoadRaceDto {
        return roadBlockageService.getCurrenRoadRace()
    }
    @MessageMapping("/clock")
    @SendTo("/topic/clock")
    fun clock(message: String): String {
        return LocalDateTime.now().toString()
    }
}