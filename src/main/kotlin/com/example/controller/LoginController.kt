package com.example.controller

import com.example.entity.Article
import com.example.enum.Exception
import com.example.exception.LocalRuntimeException
import com.example.respondWithResponse
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun login(name:String, password: String): Article {
    return Article(1, 14, password, "sd", "description")
}
fun Application.configureLogInRouting() {
    routing {

        post("/login") {
            val name = call.parameters["name"] ?: throw LocalRuntimeException(Exception.NAME_REQUEIRED)
            val password = call.parameters["password"] ?: throw LocalRuntimeException(Exception.PASSWORD_REQUEIRED)
            val result = login("name", "password")
            call.respondWithResponse(result)
        }
        post("/register") {
            val name = call.parameters["name"] ?: throw LocalRuntimeException(102,"Name is required")
            val password = call.parameters["password"] ?: throw LocalRuntimeException(103,"Password is required")
            call.respond(login(name, password))
        }

    }
}
