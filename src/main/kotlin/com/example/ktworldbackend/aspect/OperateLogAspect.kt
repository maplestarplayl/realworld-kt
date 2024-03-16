package com.example.ktworldbackend.aspect

import com.example.ktworldbackend.annotation.OperateLog
import com.example.ktworldbackend.auth.*
import com.example.ktworldbackend.entity.Profile
import com.example.ktworldbackend.enums.Role
import com.example.ktworldbackend.exception.LocalRuntimeException
import com.example.ktworldbackend.interceptor.HttpInterceptor
import com.example.ktworldbackend.service.UserService
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
@Aspect
@Component
class OperateLogAspect(val userService: UserService) {

    companion object{
        var log: Logger = LoggerFactory.getLogger(this::class.java)
    }

    @Before(" @annotation(operateLog)")
    fun before(joinPoint: JoinPoint, operateLog: OperateLog) {
        HttpInterceptor.userHolder.get()?.let {
            log.info("user info is :{}  operation start", it.username)
        }
        log.info("operateLog:{}  operation start", operateLog.value)
    }
    @AfterReturning(" @annotation(operateLog)", returning = "result")
    fun after( operateLog: OperateLog, result: Any? ) {
        log.info("operateLog:{}  operation end", operateLog.value)
        log.info("result is :{}", result)
    }
    @AfterThrowing("@annotation(operateLog)", throwing = "e")
    fun afterThrowing(joinPoint: JoinPoint, operateLog: OperateLog, e: Throwable) {
        log.error("operateLog:{}  operation error msg is {}", operateLog.value, e.message)
    }


}

inline fun <reified T> T.logger(): Logger {
    if (T::class.isCompanion) {
        return LoggerFactory.getLogger(T::class.java.enclosingClass)
    }
    return LoggerFactory.getLogger(T::class.java)
}