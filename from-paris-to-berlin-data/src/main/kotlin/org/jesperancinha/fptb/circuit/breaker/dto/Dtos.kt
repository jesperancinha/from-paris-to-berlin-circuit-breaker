package org.jesperancinha.fptb.circuit.breaker.dto

import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.jesperancinha.fptb.circuit.breaker.domain.RoadBlockTime

/**
 * Created by jofisaes on 15/10/2021
 */
data class CarDto (
    val id: Long,
    val name: String,
    val model: String,
    val location: LocationDto,
    var formerLocations: MutableSet<LocationDto>,
    var secondsHold: Long,
)

/**
 * Created by jofisaes on 15/10/2021
 */
data class LocationDto(
    val id: Long? = null,
    val name: String? = null,
    val forward: List<LocationDto> = listOf(),
    val blockageTimeTable: MutableList<RoadBlockTime> = mutableListOf(),
)

/**
 * Created by jofisaes on 14/10/2021
 */
data class RoadRaceDto(
    val cars: List<CarDto>,
    val paris: Location,
    val errorReports: MutableList<String>
)