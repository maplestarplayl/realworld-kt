package com.example.ktworldbackend.repository

import com.example.ktworldbackend.entity.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<Article,Int>{
    fun findByTitle(title: String): Article

}