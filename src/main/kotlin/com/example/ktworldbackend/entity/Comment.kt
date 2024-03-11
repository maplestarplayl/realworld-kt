package com.example.ktworldbackend.entity

import jakarta.persistence.*
import java.sql.Timestamp


@Table(name = "comment")
@Entity
data class Comment(@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_generator")
                      @SequenceGenerator(name = "comment_id_generator", sequenceName = "comment_id_seq", allocationSize = 1)
                   val id:Int,
                   val articleId:Int,
                   val authorId:Int,
                   val createdAt:Timestamp,
                   var updatedAt:Timestamp,
                   var content:String?="")
