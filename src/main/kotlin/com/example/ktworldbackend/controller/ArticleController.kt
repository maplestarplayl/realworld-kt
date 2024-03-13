package com.example.ktworldbackend.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/articles")
class ArticleController {
    @PostMapping("/")
    fun addArticle(): String {
        return "add article"
    }
}