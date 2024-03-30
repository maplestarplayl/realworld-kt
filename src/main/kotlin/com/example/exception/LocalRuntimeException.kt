package com.example.exception

import com.example.enum.Exception
import com.example.enum.getCode
import com.example.enum.getMessage
import kotlinx.serialization.Serializable

@Serializable
data class LocalRuntimeException(val code: Int, override val message: String) : RuntimeException(message) {
    constructor(exception: Exception) : this(exception.getCode(), exception.getMessage())
}
