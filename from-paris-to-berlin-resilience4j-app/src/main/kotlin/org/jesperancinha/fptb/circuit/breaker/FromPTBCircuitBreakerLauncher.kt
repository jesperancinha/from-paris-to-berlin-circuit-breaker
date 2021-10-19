package org.jesperancinha.fptb.circuit.breaker

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class FromPTBCircuitBreakerLauncher {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(FromPTBCircuitBreakerLauncher::class.java, *args)
        }
    }
}