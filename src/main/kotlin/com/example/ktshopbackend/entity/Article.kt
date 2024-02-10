package com.example.ktshopbackend.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
@Table(name = "article")
@Entity
data class Article(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id:Int, var title:String, var description:String, var content:String)
