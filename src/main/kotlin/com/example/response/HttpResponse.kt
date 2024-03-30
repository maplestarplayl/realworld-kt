package com.example.response

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class HttpResponse<T>(val success: Boolean?,val errcode: Int?, val message: String?, @Contextual val data: T?) {
}