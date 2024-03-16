package com.example.ktworldbackend.controller

import com.example.ktworldbackend.annotation.AbacAnno
import com.example.ktworldbackend.annotation.OperateLog
import com.example.ktworldbackend.auth.Action
import com.example.ktworldbackend.auth.Module
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/articles")
class ArticleController {
    @OperateLog("增加文章")
    @AbacAnno(module = Module.ARTICLE, action = Action.CREATE)
    @PostMapping()
    fun addArticle(): String {
        return "add article"
    }
    @OperateLog("查询文章")
    @AbacAnno(module = Module.ARTICLE, action = Action.READ)
    @GetMapping("/{articleId}")
    fun queryArticle(@PathVariable articleId:Int): String {
        return "query article$articleId"
    }
}