package com.example.plugins

import com.example.entity.Article
import com.example.entity.selectArticleById
import com.example.response.HttpResponse
import com.example.service.ArticleService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {

    routing {
        val articleService = ArticleService()
        get("/test/test2/{name}") {

            val name = call.parameters["name"]
            call.respond(mapOf(name to name))

        }
        post("/article"){
            val params = call.receiveParameters()
            val title = params["title"] ?: ""
            val body = params["body"] ?: ""
            val authorId = params["authorId"]?.toInt() ?: 0
            val description = params["description"] ?: ""
            val result = selectArticleById(1)
            call.respond(HttpResponse<Article>(true, 0, null, result))
        }
    }
}
