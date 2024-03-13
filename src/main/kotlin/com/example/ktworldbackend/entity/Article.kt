package com.example.ktworldbackend.entity

import jakarta.persistence.*

@Table(name = "article")
@Entity
data class Article(
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_id_generator")
    @SequenceGenerator(name = "article_id_generator", sequenceName = "article_id_seq", allocationSize = 1)
    val id:Int,
    var title:String,
    var description:String,
    var content:String,
    var authorId:Int)
