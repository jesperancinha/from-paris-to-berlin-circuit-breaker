package org.jesperancinha.fptb.circuit.breaker.domain

import org.jesperancinha.fptb.circuit.breaker.domain.Car.Companion.currentTimeStamp
import org.jesperancinha.fptb.circuit.breaker.dto.CarDto
import java.time.LocalDateTime
import java.time.OffsetTime
import java.util.concurrent.TimeUnit

/**
 * Created by jofisaes on 13/10/2021
 */
data class Car(
    val id: Long,
    val name: String,
    val model: String,
    var location: Location,
    var downtimeTSMS: Long = 0L,
    var downtimeTLMS: Long = 0L,
    var formerLocations: MutableList<Location> = mutableListOf(),
) {

    fun delay(seconds: Long) {
        if (isWaiting()) {
            downtimeTLMS += TimeUnit.SECONDS.toMillis(seconds)
        } else {
            downtimeTSMS = currentTimeStamp()
            downtimeTLMS = TimeUnit.SECONDS.toMillis(seconds)
        }
    }


    companion object {
        fun currentTimeStamp(): Long = LocalDateTime.now().toInstant(OffsetTime.now().offset).toEpochMilli()
    }
}

fun Car.isWaiting(): Boolean =
    (currentTimeStamp() - downtimeTSMS) < downtimeTLMS

fun Car.toDto(): CarDto = CarDto(
    id = this.id,
    name = this.name,
    model = this.model,
    location = this.location.toDtoAll(),
    formerLocations = this.formerLocations.toDtos(),
    secondsHold = (this.downtimeTLMS - (currentTimeStamp() - this.downtimeTSMS)) / 1000)


fun Car.moveTo(random: Location) {
    this.formerLocations.add(this.location)
    this.location = random
}

fun Car.randomFw() {
    val random = this.location.forward.random()
    moveTo(random)
}
