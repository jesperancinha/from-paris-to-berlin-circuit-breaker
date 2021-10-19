package org.jesperancinha.fptb.circuit.breaker

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class FromPTBCircuitBreakerResilience4JLauncher {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(FromPTBCircuitBreakerResilience4JLauncher::class.java, *args)
        }
    }
}