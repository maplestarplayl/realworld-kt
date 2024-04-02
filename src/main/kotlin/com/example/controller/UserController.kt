package com.example.controller

import com.example.exception.LocalRuntimeException
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun addArticle(): String {
    throw LocalRuntimeException(101,"Not implemented")
    println("dsadkljdsjda")
}
fun Application.ConfigureUserRouting(){
    routing {
        intercept(ApplicationCallPipeline.Monitoring) {
            println("Received callllllllllllllllllllllllllllllllllll")
            val request = call.request
            println("Request: $request")
            if (request.uri.contains("/login") || request.uri.contains("/articles")) {
                proceed()
            }else{
                if (request.headers["Authorization"] == null) call.respondText { "Token required" } else proceed()
            }
        }
        post("/user/addArticle"){
            val result = addArticle()
            call.respondText(result)
        }
        route("/articles"){
            get {
                call.respondText("Get all articles")
            }
            //创建一个新的文章
            post {
                call.respondText("Add new article")
            }
            //更新一个文章
            put {
                call.respondText("Update article")
            }
            delete {
                call.respondText("Delete article")
            }
        }
    }
}
