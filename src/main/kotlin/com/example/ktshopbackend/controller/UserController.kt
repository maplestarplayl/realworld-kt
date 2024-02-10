package com.example.ktshopbackend.controller

import com.example.ktshopbackend.entity.Tag
import com.example.ktshopbackend.service.TagService
import jakarta.persistence.Id
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("/user")
class UserController( val tagService: TagService) {
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