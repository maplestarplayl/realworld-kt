package com.example.ktworldbackend.repository

import com.example.ktworldbackend.entity.Tag2Article
import org.springframework.data.jpa.repository.JpaRepository

interface Tag2ArticleRepository : JpaRepository<Tag2Article,Int> {
}