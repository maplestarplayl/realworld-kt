package com.example.ktworldbackend.controller

import com.example.ktworldbackend.annotation.OperateLog
import com.example.ktworldbackend.entity.User
import com.example.ktworldbackend.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class LoginController(val userService: UserService) {


    @OperateLog("登录")
    @PostMapping("/login")
    fun login(@RequestBody map: Map<String,Any>): String {
        return userService.login(map["email"] as String, map["password"] as String)
    }
    @OperateLog("注册")
    @PostMapping("/register")
    fun register(@RequestBody map: Map<String,Any>): String {
        return userService.register(map["email"] as String, map["password"] as String, map["username"] as String)
    }
}