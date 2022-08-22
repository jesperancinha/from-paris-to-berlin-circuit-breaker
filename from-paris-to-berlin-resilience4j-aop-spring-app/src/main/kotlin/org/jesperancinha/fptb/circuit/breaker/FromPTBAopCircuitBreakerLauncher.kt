package org.jesperancinha.fptb.circuit.breaker

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class FromPTBAopCircuitBreakerLauncher : ApplicationRunner {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(FromPTBAopCircuitBreakerLauncher::class.java, *args)
        }

        var logger: Logger = LoggerFactory.getLogger(FromPTBAopCircuitBreakerLauncher::class.java)
    }

    override fun run(args: ApplicationArguments?) {
        logger.info("Circuit Breaker Service Started!")
    }
}