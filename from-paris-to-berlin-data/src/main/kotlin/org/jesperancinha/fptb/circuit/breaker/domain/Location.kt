package org.jesperancinha.fptb.circuit.breaker.domain

import org.jesperancinha.fptb.circuit.breaker.dto.LocationDto

data class Location(
    val id: Long? = null,
    val name: String,
    val forward: List<Location> = listOf(),
    val blockageTimeTable: MutableList<RoadBlockTime> = mutableListOf(),
) {
    constructor() : this(null, "Berlin")
}

fun Location.toDto(): LocationDto = LocationDto(
    id = this.id,
    name = this.name,
    forward = listOf(),
    blockageTimeTable = this.blockageTimeTable)

fun Location.toDtoAll(): LocationDto = LocationDto(
    id = this.id,
    name = this.name,
    forward = this.forward.map { location ->
        location.toDto()
    },
    blockageTimeTable = this.blockageTimeTable)

fun MutableList<Location>.toDtos(): MutableList<LocationDto> = this.map { location -> location.toDto() }.toMutableList()

