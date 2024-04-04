package com.example.util

import com.example.enum.Exception
import com.example.exception.LocalRuntimeException
import com.example.response.HttpResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
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
fun io.ktor.server.routing.Routing.interceptNotSpecified(map:Map<String,Set<HttpMethod>>) {
    intercept(ApplicationCallPipeline.Monitoring) {
        val method = call.request.local.method
        val uri = call.request.uri.replace(Regex("\\d+"), "{id}")
        //如果有对应的uri和method则说明是不需要鉴权的公有接口，继续执行
        if (map.containsKey(uri) && map[uri]!!.contains(method)) {
            proceed()
        } else {
        //如果没有对应的uri和method则说明是需要鉴权的接口，判断是否有token
            if (call.request.headers["Authorization"] == null) throw LocalRuntimeException(Exception.TOKEN_REQUEIRED)  else proceed()
        }
    }
}