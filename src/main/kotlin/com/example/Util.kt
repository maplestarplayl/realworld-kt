package com.example

import com.example.response.HttpResponse
import io.ktor.server.response.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory

//Wrapper of the respond method to send a response with a HttpResponse object
suspend inline fun <reified T : kotlin.Any> io.ktor.server.application.ApplicationCall.respondWithResponse(data: T) {
    this.respond(HttpResponse(true, 0, null, data))
}
//Util function for logging
inline fun <reified T> T.logger(): Logger {
    if (T::class.isCompanion) {
        return LoggerFactory.getLogger(T::class.java.enclosingClass)
    }
    return LoggerFactory.getLogger(T::class.java)
}