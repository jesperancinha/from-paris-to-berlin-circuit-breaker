package org.jesperancinha.fptb

import org.slf4j.Logger
import org.slf4j.LoggerFactory
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
open class FromPTBWebSocketsLauncher(
    private val template: SimpMessagingTemplate
)  : ApplicationRunner{
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(FromPTBWebSocketsLauncher::class.java, *args)
        }

        val logger: Logger = LoggerFactory.getLogger(FromPTBWebSocketsLauncher::class.java)
    }

    override fun run(args: ApplicationArguments?) {
        Timer().schedule(0, TimeUnit.SECONDS.toMillis(1)) {
            updateClock()
        }
        logger.info("Socket service started!")
    }

    fun updateClock() {
        template.convertAndSend("/topic/clock", LocalDateTime.now().toString())
    }
}