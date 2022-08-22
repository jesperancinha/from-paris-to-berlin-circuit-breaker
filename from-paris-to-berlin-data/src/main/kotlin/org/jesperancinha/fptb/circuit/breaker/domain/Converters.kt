package org.jesperancinha.fptb.circuit.breaker.domain

import org.jesperancinha.fptb.circuit.breaker.dto.CarDto
import org.jesperancinha.fptb.circuit.breaker.dto.LocationDto

fun Car.isWaiting(): Boolean =
    (Car.currentTimeStamp() - downtimeTSMS) < downtimeTLMS

fun Car.toDto(): CarDto = CarDto(
    id = this.id,
    name = this.name,
    model = this.model,
    location = this.location.toDtoAll(),
    formerLocations = this.formerLocations.toDtos(),
    secondsHold = (this.downtimeTLMS - (Car.currentTimeStamp() - this.downtimeTSMS)) / 1000
)

fun Car.moveTo(random: Location) {
    this.formerLocations.add(this.location)
    this.location = random
}

fun Car.randomFw() = moveTo(location.forward.random())

fun Location.toDto(): LocationDto = LocationDto(
    id = this.id,
    name = this.name,
    forward = listOf(),
    blockageTimeTable = this.blockageTimeTable
)

fun Location.toDtoAll(): LocationDto = LocationDto(
    id = this.id,
    name = this.name,
    forward = this.forward.map { location ->
        location.toDto()
    },
    blockageTimeTable = this.blockageTimeTable
)

fun MutableSet<Location>.toDtos() = this.map { location -> location.toDto() }.toMutableSet()
