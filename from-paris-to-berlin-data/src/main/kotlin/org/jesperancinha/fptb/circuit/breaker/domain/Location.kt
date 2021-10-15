package org.jesperancinha.fptb.circuit.breaker.domain

import com.fasterxml.jackson.annotation.JsonIgnore

data class Location(
    val id: Long? = null,
    val name: String? = null,
    @JsonIgnore
    val forward: List<Location> = listOf(),
    val blockageTimeTable: MutableList<RoadBlockTime> = mutableListOf()
) {
    constructor() : this(null)
}