package org.jesperancinha.fptb.circuit.breaker

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.messaging.simp.SimpMessagingTemplate
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule

@SpringBootApplication
open class FromPTBAopCircuitBreakerLauncher(
    private val template: SimpMessagingTemplate
)  : ApplicationRunner{
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Hello, World")
            SpringApplication.run(FromPTBAopCircuitBreakerLauncher::class.java, *args)
        }
    }

    override fun run(args: ApplicationArguments?) {
        Timer().schedule(0, TimeUnit.SECONDS.toMillis(1)) {
            updateClock()
        }
    }

    fun updateClock() {
        template.convertAndSend("/topic/clock", LocalDateTime.now().toString())
    }
}