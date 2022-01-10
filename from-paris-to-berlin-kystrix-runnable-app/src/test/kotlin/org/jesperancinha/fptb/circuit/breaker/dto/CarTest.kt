package org.jesperancinha.fptb.circuit.breaker.dto

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

private const val testBrand1 = "Tesla"

internal class CarTest {

    @Test
    fun `should get model`() {
        val car = Car(testBrand1)

        car.model shouldBe testBrand1
    }
}