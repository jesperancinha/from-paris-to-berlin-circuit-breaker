package org.jesperancinha.fptb.circuit.breaker.service

import org.jesperancinha.fptb.circuit.breaker.domain.RoadBlockagesMap
import org.jesperancinha.fptb.circuit.breaker.dto.Location
import org.springframework.stereotype.Service

/**
 * Created by jofisaes on 15/10/2021
 */
@Service
class RoadBlockageService(
    private val roadBlockagesMap: RoadBlockagesMap,
) {
    fun setRoadBlock(location: Location) {
        roadBlockagesMap
    }

    fun getStartLocation(): Location = roadBlockagesMap.location

    fun startGame() {

    }
}