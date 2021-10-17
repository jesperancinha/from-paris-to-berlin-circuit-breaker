package org.jesperancinha.fptb.circuit.breaker.domain

import org.jesperancinha.fptb.circuit.breaker.domain.Car.Companion.currentTimeStamp
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
