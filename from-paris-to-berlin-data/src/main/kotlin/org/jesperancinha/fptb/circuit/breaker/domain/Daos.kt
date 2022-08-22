package org.jesperancinha.fptb.circuit.breaker.domain

import java.io.Serializable
import java.time.LocalDateTime
import java.time.OffsetTime
import java.util.*
import java.util.concurrent.TimeUnit

enum class BlockageType {
    TIMEOUT,
    ERROR,
    HALF_PERCENT_FAIL,
    NONE,
    UNKNOWN
}

/**
 * Created by jofisaes on 14/10/2021
 */
data class RoadBlockTime(
    val minute: Int,
    val blockageType: BlockageType
) : Serializable

data class Location(
    val id: Long? = null,
    val name: String,
    val forward: List<Location> = listOf(),
    val blockageTimeTable: MutableList<RoadBlockTime> = mutableListOf(),
) : Serializable {
    constructor() : this(null, "Berlin")

    override fun hashCode(): Int = Objects.hash(id)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Location) return false
        if (id != other.id) return false
        return true
    }

}


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
    var formerLocations: MutableSet<Location> = mutableSetOf(),
) : Serializable {

    fun delay(seconds: Long) {
        if (isWaiting()) {
            downtimeTLMS += TimeUnit.SECONDS.toMillis(seconds)
        } else {
            downtimeTSMS = currentTimeStamp()
            downtimeTLMS = TimeUnit.SECONDS.toMillis(seconds)
        }
    }

    override fun hashCode(): Int = Objects.hashCode(id)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Car) return false
        if (id != other.id) return false
        return true
    }


    companion object {
        fun currentTimeStamp(): Long = LocalDateTime.now().toInstant(OffsetTime.now().offset).toEpochMilli()
    }
}
