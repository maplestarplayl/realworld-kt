package com.example.plugins

import com.example.GsonContentConverter
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        //json()
        gson {
            register(ContentType.Application.Json, GsonContentConverter() )
        }

    }
    routing {

        get("/json/kotlinx-serialization") {

            call.respond(mapOf("hello" to "world"))
        }
        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}
