package org.jesperancinha.fptb.kystrix

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.create
import org.springframework.web.reactive.function.client.bodyToMono
import se.haleby.kystrix.hystrixObservableCommand
import se.haleby.kystrix.monoCommand
import se.haleby.kystrix.toMono


@SpringBootApplication
open class Hello {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val webClient: WebClient = create()

            println("Hello, World")
            val greeting = hystrixObservableCommand<String> {
                groupKey("Test")
                commandKey("Test-Command")
                monoCommand {
                    webClient.get().uri("/cars/1").retrieve().bodyToMono()
                }
                commandProperties {
                    withExecutionTimeoutInMilliseconds(10000)
                    withFallbackEnabled(false)
                }
            }.toMono()

            SpringApplication.run(Hello::class.java, *args)
        }
    }
}