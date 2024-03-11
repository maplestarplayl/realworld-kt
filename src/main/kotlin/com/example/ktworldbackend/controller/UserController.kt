package com.example.ktworldbackend.controller

import com.example.ktworldbackend.entity.Profile
import com.example.ktworldbackend.entity.Tag
import com.example.ktworldbackend.interceptor.HttpInterceptor
import com.example.ktworldbackend.service.TagService
import com.example.ktworldbackend.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("/user")
class UserController( val tagService: TagService, val userService: UserService) {
    @GetMapping("/profile")
    fun getCurrentUser(): Profile{
        val httpInterceptor = HttpInterceptor()
        val user: Profile = httpInterceptor.userHolder.get()
        val profile = userService.getCurrentUser(user)
        return profile
    }
    @PostMapping("/tag")
    fun createTag(@RequestParam tagName:String): String{
        tagService.createNewTag(tagName)
        return "ok"
    }
    @GetMapping("/tag")
    fun queryTagById(@RequestParam id: Int):Optional<Tag>{
        return tagService.findTagById(id)
    }
}