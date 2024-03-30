package com.example.enum

enum class Exception( val code: Int,
                       val message: String) {

        NAME_REQUEIRED(code =102, message = "Name is required"),
        PASSWORD_REQUEIRED(103,"Password is required"),
        USER_NOT_FOUND(404, "User not found"),
        USERNAME_ALREADY_EXISTS(409, "UserName already exists")

}
fun Exception.getCode(): Int {
    return this.code
}
fun Exception.getMessage(): String {
    return this.message
}