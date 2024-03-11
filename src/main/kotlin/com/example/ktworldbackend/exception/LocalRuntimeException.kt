package com.example.ktworldbackend.exception

import lombok.Data
import lombok.EqualsAndHashCode

@Data
@EqualsAndHashCode(callSuper = true)
class LocalRuntimeException : RuntimeException{
    private val code: Int
    override val message: String

    constructor(code: Int, message: String) {
        this.code = code
        this.message = message
    }

    constructor(message: String) {
        this.code = 500
        this.message = message
    }
}