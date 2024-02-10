package com.example.ktshopbackend.repository

import com.example.ktshopbackend.entity.Tag2Article
import org.springframework.data.jpa.repository.JpaRepository

interface Tag2ArticleRepository : JpaRepository<Tag2Article,Int> {
}