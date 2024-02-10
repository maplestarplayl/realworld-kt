package com.example.ktshopbackend.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.sql.Timestamp


@Table(name = "comment")
@Entity
data class Comment(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)val id:Int, val articleId:Int, val authorId:Int, val createdAt:Timestamp, var updatedAt:Timestamp, var content:String?="")
