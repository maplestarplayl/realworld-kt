package com.example.ktshopbackend.entity

import jakarta.persistence.*

@Entity
@Table(name="tag2article")
data class Tag2Article(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)val id:Int, val tagId:Int, val articleId:Int)
