package org.jesperancinha.fptb.circuit.breaker.serializers

import com.hazelcast.nio.ObjectDataInput

import com.hazelcast.nio.ObjectDataOutput
import com.hazelcast.nio.serialization.StreamSerializer
import org.jesperancinha.fptb.circuit.breaker.domain.Car
import org.jesperancinha.fptb.circuit.breaker.domain.Location
import org.jesperancinha.fptb.circuit.breaker.domain.RoadBlockTime
import java.io.*


fun <T> ByteArray.toObject(): T {
    val byteArrayInputStream = ByteArrayInputStream(this)
    val objectInput: ObjectInput
    objectInput = ObjectInputStream(byteArrayInputStream)
    val result = objectInput.readObject() as T
    objectInput.close()
    byteArrayInputStream.close()
    return result
}

fun Serializable.toByteArray(): ByteArray {
    val byteArrayOutputStream = ByteArrayOutputStream()
    val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
    objectOutputStream.writeObject(this)
    objectOutputStream.flush()
    val result = byteArrayOutputStream.toByteArray()
    byteArrayOutputStream.close()
    objectOutputStream.close()
    return result
}

/**
 * Created by jofisaes on 21/08/2022
 */
class LocationStreamSerializer : StreamSerializer<Location> {
    override fun getTypeId(): Int {
        return 1
    }

    override fun write(p0: ObjectDataOutput, location: Location) {
        p0.writeByteArray(location.toByteArray())
    }

    override fun read(p0: ObjectDataInput): Location = p0.readByteArray()?.toObject<Location>() ?: throw RuntimeException("Failed to deserialize location!")

    override fun destroy() {}
}

class CarStreamSerializer : StreamSerializer<Car> {
    override fun getTypeId(): Int {
        return 1
    }

    override fun write(p0: ObjectDataOutput, car: Car) {
        p0.writeByteArray(car.toByteArray())
    }

    override fun read(p0: ObjectDataInput): Car = p0.readByteArray()?.toObject<Car>() ?: throw RuntimeException("Failed to deserialize car!")

    override fun destroy() {}
}

class RoadBlockTimeStreamSerializer : StreamSerializer<RoadBlockTime> {
    override fun getTypeId(): Int {
        return 1
    }

    override fun write(p0: ObjectDataOutput, car: RoadBlockTime) {
        p0.writeByteArray(car.toByteArray())
    }

    override fun read(p0: ObjectDataInput): RoadBlockTime = p0.readByteArray()?.toObject<RoadBlockTime>() ?: throw RuntimeException("Failed to deserialize roadBlockTime!")

    override fun destroy() {}
}