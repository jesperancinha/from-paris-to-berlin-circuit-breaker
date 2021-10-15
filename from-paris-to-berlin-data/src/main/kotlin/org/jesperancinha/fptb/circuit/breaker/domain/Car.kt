package org.jesperancinha.fptb.circuit.breaker.domain

/**
 * Created by jofisaes on 13/10/2021
 */
data class Car(
    val id: Long,
    val name: String,
    val model: String,
    var location: Location,
)