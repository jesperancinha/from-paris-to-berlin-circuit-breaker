package org.jesperancinha.fptb.circuit.breaker.dto

import org.jesperancinha.fptb.circuit.breaker.domain.BlockageType
import org.jesperancinha.fptb.circuit.breaker.domain.Car
import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.jesperancinha.fptb.circuit.breaker.domain.RoadBlockTime
import org.springframework.stereotype.Component
import kotlin.random.Random

/**
 * Created by jofisaes on 14/10/2021
 */
data class RoadRaceDto(
    val cars: List<CarDto>,
    val paris: Location,
    val errorReports: MutableList<String>
)