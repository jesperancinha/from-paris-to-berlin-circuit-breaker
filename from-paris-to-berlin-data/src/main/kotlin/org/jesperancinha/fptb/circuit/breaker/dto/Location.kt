package org.jesperancinha.fptb.circuit.breaker.dto

data class Location(
    val id: Long,
    val blockageType: BlockageType,
    val name: String,
    val forward: List<Location>
)