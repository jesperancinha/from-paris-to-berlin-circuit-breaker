package org.jesperancinha.fptb.circuit.breaker.dto

/**
 * Created by jofisaes on 14/10/2021
 */
data class RoadBlockTime(
    val minute: Long,
    val blockageType: BlockageType
)