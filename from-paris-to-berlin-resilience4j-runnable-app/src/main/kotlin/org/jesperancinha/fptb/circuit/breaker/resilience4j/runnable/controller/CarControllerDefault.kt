package org.jesperancinha.fptb.circuit.breaker.resilience4j.runnable.controller

import io.github.resilience4j.kotlin.timelimiter.decorateSuspendFunction
import io.github.resilience4j.timelimiter.TimeLimiter
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.jesperancinha.fptb.circuit.breaker.resilience4j.runnable.dto.Car
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Duration


/**
 * Created by jofisaes on 13/10/2021
 */
@RestController
@RequestMapping("/cars/default")
class CarControllerDefault {

    var timeLimiterConfig: TimeLimiterConfig = TimeLimiterConfig.custom()
        .timeoutDuration(Duration.ofMillis(100))
        .build()

    var timeLimiter: TimeLimiter = TimeLimiter.of("backendName", timeLimiterConfig)

    private suspend fun getPublicCar(): Car {
        return timeLimiter.decorateSuspendFunction {
            getPrivateCar()
        }.let { funct ->
            try {
                funct()
            } catch (exception: Exception) {
                Car("Opel Corsa")
            }
        }
    }

    private suspend fun getPrivateCar(): Car {
        delay(10000)
        return Car("Lancya")
    }


    @GetMapping("/normal/{id}")
    private fun getCars(@PathVariable id: Long): Car {
        return runBlocking {
            getPublicCar()
        }
    }

    @GetMapping("/suspend/{id}")
    private fun getCarros(@PathVariable id: Long): Car {
        return Car("Laborghini")
    }
}