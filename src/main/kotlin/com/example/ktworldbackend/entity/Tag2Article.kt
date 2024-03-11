package com.example.ktworldbackend.entity

import jakarta.persistence.*

@Entity
@Table(name="tag2article")
data class Tag2Article(@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag2article_id_generator")
                        @SequenceGenerator(name = "tag2article_id_generator", sequenceName = "tag2article_id_seq", allocationSize = 1)
                       val id:Int,
                       val tagId:Int,
                       val articleId:Int)
