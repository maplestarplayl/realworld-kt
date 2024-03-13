package com.example.ktworldbackend.aspect

import com.example.ktworldbackend.annotation.OperateLog
import com.example.ktworldbackend.interceptor.HttpInterceptor
import lombok.extern.slf4j.Slf4j
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
@Aspect
@Component
@Slf4j
class OperateLogAspect {

    companion object{
        var log: Logger = LoggerFactory.getLogger(this::class.java)
    }
    @Pointcut("@annotation(com.example.ktworldbackend.annotation.OperateLog)")
    fun operateLogPointcut() {
    }

    @Before("operateLogPointcut() && @annotation(operateLog)")
    fun before(operateLog: OperateLog) {
        HttpInterceptor.userHolder.get()?.let {
            log.info("user info is :{}  operation start", it.username)
        }
        log.info("operateLog:{}  operation start", operateLog.value)
    }
    @AfterReturning("operateLogPointcut() && @annotation(operateLog)", returning = "result")
    fun after( operateLog: OperateLog, result: Any? ) {
        log.info("operateLog:{}  operation end", operateLog.value)
        log.info("result is :{}", result)
    }
    @AfterThrowing("operateLogPointcut() && @annotation(operateLog)", throwing = "e")
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