package org.jesperancinha.fptb.circuit.breaker.service

import org.jesperancinha.fptb.circuit.breaker.adapters.RoadRace
import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.springframework.stereotype.Service

/**
 * Created by jofisaes on 15/10/2021
 */
@Service
class RoadBlockageService(
    private val roadRace: RoadRace
) {
    fun setRoadBlock(location: Location) {
        roadRace.location = location
    }

    fun getStartLocation(): Location = roadRace.location

    fun startGame() {
        roadRace.cars;

    }
}