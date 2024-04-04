package com.example.controller

import com.example.entity.Article
import com.example.entity.selectAllArticles
import com.example.entity.selectArticleById
import com.example.entity.updateArticle
import com.example.enum.Exception
import com.example.exception.LocalRuntimeException
import com.example.util.interceptNotSpecified
import com.example.util.respondWithResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun addArticle(): String {
    throw LocalRuntimeException(Exception.NOT_IMPLEMENTED)
}
fun Application.ConfigureUserRouting(){
    routing {

        interceptNotSpecified(mapOf("/articles" to setOf(HttpMethod.Get,HttpMethod.Put),"/articles/{id}" to setOf(HttpMethod.Get,HttpMethod.Put)))
        route("/articles"){
            get("{id}") {
                val result = selectArticleById(call.parameters["id"]?.toInt() ?: throw LocalRuntimeException(Exception.PARAM_MISSED))
                call.respondWithResponse(result)
            }
            get {
                val result = selectAllArticles()
                call.respondWithResponse(result)
            }
            //创建一个新的文章
            post {
                call.respondWithResponse("Add new article")
            }
            //更新一个文章
            put {
                val article = call.receiveNullable<Article>()
                //val art = Json.decodeFromJsonElement(Article.serializer(), Json.parseToJsonElement(article))
                //print(art)
                updateArticle(article!!)
                call.respondWithResponse("Update article")
            }
            delete {
                call.respondWithResponse("Delete article")
            }
        }
    }
}
