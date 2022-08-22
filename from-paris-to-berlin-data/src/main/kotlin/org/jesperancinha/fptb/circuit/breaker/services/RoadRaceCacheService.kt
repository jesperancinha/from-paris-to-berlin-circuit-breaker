package org.jesperancinha.fptb.circuit.breaker.services

import com.hazelcast.collection.IList
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.jesperancinha.fptb.circuit.breaker.domain.*
import org.jesperancinha.fptb.circuit.breaker.domain.Car.Companion.currentTimeStamp
import org.jesperancinha.fptb.circuit.breaker.exception.PlayerNotFoundException
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit
import kotlin.random.Random


private const val MAIN_CACHE = "fptb"

/**
 * Created by jofisaes on 14/10/2021
 */
@Component
class RoadRaceCacheService {

    private var hzInstance: HazelcastInstance = Hazelcast.newHazelcastInstance()

    private var timeTables: MutableMap<String, List<RoadBlockTime>>
        get() = hzInstance.getMap<String, MutableMap<String, List<RoadBlockTime>>>(MAIN_CACHE)["timeTables"]
            ?: throw RuntimeException("Roadblock map not initialized!")
        set(value) {
            hzInstance.getMap<String, MutableMap<String, List<RoadBlockTime>>>(MAIN_CACHE)["timeTables"] = value
        }

    var cars
        get() = hzInstance.getSet<Car>("cars").toMutableSet()
        set(value) {
            hzInstance.getSet<Car>("cars").apply {
                clear()
                addAll(value)
            }
        }

    var paris: Location
        get() = hzInstance.getList<Location>("location").let { if (it.isEmpty()) Location() else it[0] }
        set(location) {
            hzInstance.getList<Location>("location").apply {
                clear()
                add(location)
            }
        }

    var errorReports
        get() = hzInstance.getList<String>("errorReports").toMutableList()
        set(value) {
            hzInstance.getList<String>("errorReports").apply {
                clear()
                addAll(value)
            }
        }

    fun init() {
        errorReports = mutableListOf()
        val newTT = mutableMapOf<String, List<RoadBlockTime>>()
        val newParis = paris
        newTT.addTimeTables(newParis)
        paris = newParis
        timeTables = newTT
        cars = (1..5).map {
            Car(
                id = it.toLong(),
                name = "name",
                model = "brand",
                location = newParis,
                downtimeTSMS = currentTimeStamp(),
                downtimeTLMS = TimeUnit.SECONDS.toMillis(10),
                formerLocations = mutableSetOf(newParis)
            )
        }.toMutableSet()
    }


    fun randomMoveFw() {
        cars = cars
            .map {
                if (it.id != 5L && !it.isWaiting()) {
                    if (it.location.forward.isNotEmpty()) {
                        it.location = it.location.forward.random()
                        it.delay(Random.nextLong(10, 30))
                        it.formerLocations.add(it.location)
                    }
                }
                it
            }.toMutableSet()
    }
}

fun MutableMap<String, List<RoadBlockTime>>.addTimeTables(location: Location) {
    location.apply {
        val roadBlockTime = get(name)
        if (roadBlockTime == null) {
            blockageTimeTable.addAll((0..(Random.nextInt(0, 3) + 1))
                .map { RoadBlockTime(Random.nextInt(10), BlockageType.values().random()) })
            set(name, blockageTimeTable)
        } else if (blockageTimeTable.isEmpty()) {
            blockageTimeTable.addAll(roadBlockTime)
        }
        forward.forEach { addTimeTables(it) }
    }
}

fun RoadRaceCacheService.getMyCar(): Car {
    return this.cars.find { it.id == 5L } ?: throw PlayerNotFoundException()
}
