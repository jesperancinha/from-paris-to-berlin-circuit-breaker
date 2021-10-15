package org.jesperancinha.fptb.circuit.breaker.domain

data class Location(
    val id: Long? = null,
    val name: String? = null,
    val forward: List<Location> = listOf(),
    val blockageTimeTable: List<RoadBlockTime> = mutableListOf()
) {
    constructor() : this(null)
}