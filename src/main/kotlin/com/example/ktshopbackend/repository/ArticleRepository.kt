package com.example.ktshopbackend.repository

import com.example.ktshopbackend.entity.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<Article,Int>{

}