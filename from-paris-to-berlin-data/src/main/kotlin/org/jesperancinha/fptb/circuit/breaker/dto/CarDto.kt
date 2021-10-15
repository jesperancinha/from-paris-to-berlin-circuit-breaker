package org.jesperancinha.fptb.circuit.breaker.dto

import org.jesperancinha.fptb.circuit.breaker.domain.Location

/**
 * Created by jofisaes on 15/10/2021
 */
data class CarDto (
    val id: Long,
    val name: String,
    val model: String,
    val location: LocationDto,
)