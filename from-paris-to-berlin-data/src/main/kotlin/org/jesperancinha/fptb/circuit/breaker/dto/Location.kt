package org.jesperancinha.fptb.circuit.breaker.dto

import org.jesperancinha.fptb.circuit.breaker.dto.BlockageType.UNKNOWN

data class Location(
    val id: Long? = null,
    val name: String? = null,
    val forward: List<Location> = listOf(),
    val blockageType: BlockageType = UNKNOWN,
) {
    constructor() : this(null)
}