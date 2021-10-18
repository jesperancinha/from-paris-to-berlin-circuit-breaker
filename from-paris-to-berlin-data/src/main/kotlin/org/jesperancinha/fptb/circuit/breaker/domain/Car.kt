package org.jesperancinha.fptb.circuit.breaker.domain

import org.jesperancinha.fptb.circuit.breaker.domain.Car.Companion.currentTimeStamp
import org.jesperancinha.fptb.circuit.breaker.dto.CarDto
import java.time.LocalDateTime
import java.time.OffsetTime

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

    fun delay(minutes: Long) {
//        if (isWaiting()) {
//            downtimeTLMS += TimeUnit.MINUTES.toMillis(minutes)
//        } else {
//            downtimeTLMS = currentTimeStamp()
//            downtimeTLMS = TimeUnit.MINUTES.toMillis(minutes)
//        }
    }


    companion object {
        fun currentTimeStamp(): Long = LocalDateTime.now().toInstant(OffsetTime.now().offset).toEpochMilli()
    }
}

fun Car.isWaiting(): Boolean =
    currentTimeStamp() - downtimeTSMS < downtimeTLMS

fun Car.toDto(): CarDto = CarDto(
    id = this.id,
    name = this.name,
    model = this.model,
    location = this.location.toDtoAll(),
    formerLocations = this.formerLocations.toDtos())

