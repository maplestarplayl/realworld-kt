package com.example

import com.example.config.DB
import com.example.controller.ConfigureUserRouting
import com.example.controller.configureLogInRouting
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args :Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args))
        .start(wait = true)

}

fun Application.module() {
    DB.init(environment.config)
    configureLogInRouting()
    configureSecurity()
    configureMonitoring()
    configureSerialization()
    ConfigureUserRouting()
    configureStatusPages()
    configureDataTransformation()
}
