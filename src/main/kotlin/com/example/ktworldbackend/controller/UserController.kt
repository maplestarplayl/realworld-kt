package com.example.ktworldbackend.controller

import com.example.ktworldbackend.annotation.AbacAnno
import com.example.ktworldbackend.annotation.OperateLog
import com.example.ktworldbackend.auth.Action
import com.example.ktworldbackend.auth.Module
import com.example.ktworldbackend.entity.Profile
import com.example.ktworldbackend.entity.Tag
import com.example.ktworldbackend.interceptor.HttpInterceptor
import com.example.ktworldbackend.service.TagService
import com.example.ktworldbackend.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("/user")
class UserController( val tagService: TagService, val userService: UserService) {
    @OperateLog(description = "获取当前用户信息")
    @GetMapping("/profile")
    fun getCurrentUser(): Profile{
        return userService.getCurrentProfile(HttpInterceptor.userHolder.get())
    }
    @OperateLog("更新当前用户信息")
    @PutMapping("/profile")
    fun updateCurrentUser(@RequestBody profile: Profile): Profile{
        return userService.updateCurrentUser(HttpInterceptor.userHolder.get(), profile)
    }
    @AbacAnno(module = Module.TAG, action = Action.CREATE)
    @OperateLog("创建标签" )
    @PostMapping("/tag")
    fun createTag(@RequestParam tagName:String): String{
        tagService.createNewTag(tagName)
        return "ok"
    }
    @AbacAnno(module = Module.TAG, action = Action.READ)
    @OperateLog("根据id获取标签")
    @GetMapping("/tag")
    fun queryTagById(@RequestParam id: Int):Optional<Tag>{
        return tagService.findTagById(id)
    }


}