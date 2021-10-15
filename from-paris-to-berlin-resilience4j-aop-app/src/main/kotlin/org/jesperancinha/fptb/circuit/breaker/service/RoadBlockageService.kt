package org.jesperancinha.fptb.circuit.breaker.service

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.jesperancinha.fptb.circuit.breaker.adapters.RoadRace
import org.jesperancinha.fptb.circuit.breaker.domain.Car
import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.springframework.stereotype.Service

/**
 * Created by jofisaes on 15/10/2021
 */
@Service
class RoadBlockageService(
    private val roadRace: RoadRace
) {
    suspend fun setRoadBlock(location: Location) {
        roadRace.paris = location
        startGame()
    }

    fun getStartLocation(): Location = roadRace.paris

    private suspend fun startGame() {
        coroutineScope {
            launch {
                roadRace.init();
            }
        }
    }

    fun getCurrenRoadRace(): RoadRace? {
        return roadRace
    }
}