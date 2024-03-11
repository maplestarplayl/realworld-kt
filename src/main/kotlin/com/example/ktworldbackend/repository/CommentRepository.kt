package com.example.ktworldbackend.repository

import com.example.ktworldbackend.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment,Int>{
}