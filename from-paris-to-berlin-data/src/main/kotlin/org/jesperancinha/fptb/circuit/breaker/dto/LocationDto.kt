package org.jesperancinha.fptb.circuit.breaker.dto

import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.jesperancinha.fptb.circuit.breaker.domain.RoadBlockTime

/**
 * Created by jofisaes on 15/10/2021
 */
data class LocationDto(
    val id: Long? = null,
    val name: String? = null,
    val forward: List<LocationDto> = listOf(),
    val blockageTimeTable: MutableList<RoadBlockTime> = mutableListOf(),
) {
    constructor() : this(null)
}