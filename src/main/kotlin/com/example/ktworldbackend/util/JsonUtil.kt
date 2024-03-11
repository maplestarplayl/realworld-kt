package com.example.ktworldbackend.util

import com.fasterxml.jackson.databind.ObjectMapper

object JsonUtil{
    private val objectMapper: ObjectMapper = ObjectMapper()
    fun toJson(obj: Any): String {
        return objectMapper.writeValueAsString(obj)
    }
    fun <T> toObject(json: String, clazz: Class<T>): T {
        return objectMapper.readValue(json, clazz)
    }
}