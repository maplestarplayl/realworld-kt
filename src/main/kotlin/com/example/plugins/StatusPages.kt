package com.example.plugins

import com.example.exception.LocalRuntimeException
import com.example.response.HttpResponse
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages(){
    install(StatusPages){
        exception<Throwable>{call,cause->
            if (cause is LocalRuntimeException) {
                call.respond(HttpResponse<LocalRuntimeException>(false, cause.code, cause.message, null))
            }
            call.respond(cause.message ?: "Unknown error")
        }
    }
}