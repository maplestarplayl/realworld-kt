package com.example.ktshopbackend.repository

import com.example.ktshopbackend.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment,Int>{
}