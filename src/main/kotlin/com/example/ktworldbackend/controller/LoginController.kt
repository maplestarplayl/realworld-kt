package com.example.ktworldbackend.controller

import com.example.ktworldbackend.entity.User
import com.example.ktworldbackend.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class LoginController(val userService: UserService) {
    @PostMapping("/login")
    fun login(@RequestBody map: Map<String,Any>): String {
        return userService.login(map.get("email")as String, map.get("password")as String)
    }
    @PostMapping("/register")
    fun register(@RequestBody map: Map<String,Any>): String {
        return userService.register(map.get("email")as String, map.get("password")as String, map.get("username")as String)
    }
}