package com.example

import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.serialization.*
import io.ktor.util.reflect.*
import io.ktor.utils.io.*
import io.ktor.utils.io.charsets.*
import io.ktor.utils.io.core.*
import kotlin.text.toByteArray

public class GsonContentConverter: ContentConverter{
    private val gson = Gson()
    override suspend fun serialize(contentType: ContentType, charset: Charset, typeInfo: TypeInfo, value: Any): OutgoingContent? {
        val json = gson.toJson(value)
        val bytes = json.toByteArray(charset)
        return ByteArrayContent(bytes, contentType)
    }
    override suspend fun deserialize(charset: Charset, typeInfo: TypeInfo, content: ByteReadChannel): Any?{
        val json = content.readRemaining().readText(charset)
        println("Convertttingggggggggg")
        return gson.fromJson(json, typeInfo.reifiedType)

    }
}
