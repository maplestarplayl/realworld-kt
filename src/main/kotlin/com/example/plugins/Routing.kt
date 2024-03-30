package com.example.plugins

import com.example.entity.Article
import com.example.response.HttpResponse
import com.example.service.ArticleService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {

    routing {
        val articleService = ArticleService()
        intercept(ApplicationCallPipeline.Monitoring) {
            println("Received callllllllllllllllllllllllllllllllllll")
            val request = call.request
            println("Request: $request")
            if (request.uri == "/login" || request.uri == "/articles") {
                proceed()
            }else{
                 if (request.headers["Authorization"] == null) call.respondText { "Token required" } else proceed()
            }

        }
        get("/test/test2/{name}") {
            val name = call.parameters["name"]
            call.respond(mapOf(name to name))

        }
        get("/articles"){
            val result = articleService.allArticles()
            call.respond(HttpResponse<List<Article>>(true, 0, null, result))
        }
        post("/article"){
            val params = call.receiveParameters()
            val title = params["title"] ?: ""
            val body = params["body"] ?: ""
            val authorId = params["authorId"]?.toInt() ?: 0
            val description = params["description"] ?: ""
            val result = articleService.addNewArticle(title, body, authorId, description)
            call.respond(HttpResponse<Article>(true, 0, null, result))
        }
    }
}
