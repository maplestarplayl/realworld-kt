package com.example.entity

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Article(val id: Int, val authorId: Int,val title: String, val body: String, val description: String)
object Articles : Table() {
    val id = integer("id").autoIncrement()
    val authorId = integer("authorId")
    val title = varchar("title", 128)
    val body = varchar("body", 1024)
    val description = varchar("description", 256)
    override val primaryKey = PrimaryKey(id)
}