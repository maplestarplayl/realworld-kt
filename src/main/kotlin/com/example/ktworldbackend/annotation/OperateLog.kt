package com.example.ktworldbackend.annotation

import com.example.ktworldbackend.auth.Action
import com.example.ktworldbackend.auth.Module
import org.springframework.core.annotation.AliasFor

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
annotation class OperateLog(
    @get:AliasFor(attribute = "description") val value: String = "",
    @get:AliasFor(attribute = "value") val description: String = "",
)