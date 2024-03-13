package com.example.ktworldbackend.interceptor

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.ktworldbackend.aspect.OperateLogAspect.Companion.log
import com.example.ktworldbackend.entity.Profile
import com.example.ktworldbackend.exception.LocalRuntimeException
import com.example.ktworldbackend.service.secret
import com.example.ktworldbackend.util.JsonUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.lang.NonNull
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler


@Component
class HttpInterceptor : HandlerInterceptor{

    companion object{
         var userHolder: ThreadLocal<Profile> = ThreadLocal()
     }
    override fun preHandle(@NonNull  request: HttpServletRequest, @NonNull  response: HttpServletResponse, @NonNull  handler: Any):Boolean{

        if (handler is ResourceHttpRequestHandler) return true

        //var method: Method = (handler as HandlerMethod).method
        val token: String = request.getHeader("token") ?: throw LocalRuntimeException("token is required")
        val profile = JsonUtil.toObject(JWT.require(Algorithm.HMAC256(secret)).build().verify(token).getClaim("user").asString() , Profile::class.java)
        userHolder.set(profile)
        log.info("user info is :{}  operation start", profile.username)
        return true
    }
    override fun afterCompletion(@NonNull request: HttpServletRequest, @NonNull response: HttpServletResponse, @NonNull handler: Any, @NonNull ex: Exception?){
        userHolder.remove()
    }
}