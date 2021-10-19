package org.jesperancinha.fptb.circuit.breaker

import org.jesperancinha.fptb.circuit.breaker.dto.Car
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.create
import org.springframework.web.reactive.function.client.bodyToMono
import se.haleby.kystrix.hystrixObservableCommand
import se.haleby.kystrix.monoCommand
import se.haleby.kystrix.toMono


@SpringBootApplication
open class FromPTBCircuitBreakerKystrixLauncher : ApplicationRunner {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(FromPTBCircuitBreakerKystrixLauncher::class.java, *args)
        }
    }

    override fun run(args: ApplicationArguments?) {
        val webClient: WebClient = create("http://localhost:8080")
        hystrixObservableCommand<Car> {
            groupKey("Test")
            commandKey("Test-Command")
            monoCommand {
                webClient.get().uri("/cars/carros/1").retrieve().bodyToMono()
            }
            commandProperties {
                withExecutionTimeoutInMilliseconds(10000)
                withFallbackEnabled(false)
            }
        }.toMono().block()
    }
}