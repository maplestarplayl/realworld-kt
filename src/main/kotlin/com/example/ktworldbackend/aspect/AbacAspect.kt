package com.example.ktworldbackend.aspect

import com.example.ktworldbackend.annotation.AbacAnno
import com.example.ktworldbackend.annotation.OperateLog
import com.example.ktworldbackend.auth.*
import com.example.ktworldbackend.entity.User
import com.example.ktworldbackend.enums.Role
import com.example.ktworldbackend.exception.LocalRuntimeException
import com.example.ktworldbackend.interceptor.HttpInterceptor
import com.example.ktworldbackend.service.UserService
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Aspect
@Component
class AbacAspect(val userService: UserService) {

    @Before("@annotation(abacAnno)")
    fun before(joinPoint: JoinPoint, abacAnno: AbacAnno) {
        val user: User = HttpInterceptor.userHolder.get()
                            ?.run { userService.getCurrentUser(this) }
                            ?: throw RuntimeException("user is required")
        rule {
            policy {
                role = Role.USER
                modules = listOf(Module.TAG)
                action = listOf( Action.READ)
                strategy = Strategy.ALLOW
            }
            policy {
                role = Role.USER
                modules = listOf(Module.ARTICLE)
                action = listOf( Action.READ, Action.CREATE, Action.UPDATE, Action.DELETE)
                strategy = Strategy.ALLOW
            }
            policy {
                role = Role.ADMIN
                modules = listOf(Module.TAG)
                action = listOf( Action.CREATE, Action.READ, Action.UPDATE, Action.DELETE)
                strategy = Strategy.ALLOW
            }
            policy {
                role = Role.ADMIN
                modules = listOf(Module.ARTICLE)
                action = listOf(Action.READ, Action.DELETE)
                strategy = Strategy.ALLOW
            }
        }
        if (!Verifer.verify(Rule, abacAnno.action,Attribute(user,abacAnno.module))) throw LocalRuntimeException("no permission")
    }

}