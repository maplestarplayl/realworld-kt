package com.example.ktworldbackend.exception

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler  {
    @ExceptionHandler(LocalRuntimeException::class)
    fun  localException(e: LocalRuntimeException?): String? {
        return "ErrMsg: ${e?.message}"
    }
}