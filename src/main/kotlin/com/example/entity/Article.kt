package com.example.entity

import com.example.DB
import com.example.enum.Exception
import com.example.exception.LocalRuntimeException
import com.example.util.updateWithObject
import kotlinx.serialization.Serializable
import org.ktorm.dsl.*
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

@Serializable
data class Article(val id: Int, val authorId: Int?, val title: String, val body: String, val description: String)
object Articles : Table<Nothing>("article") {
    val id = int("id").primaryKey()
    val authorId = int("author_id")
    val title = varchar("title")
    val body = varchar("body",)
    val description = varchar("description")
}
fun Query.mapRowToResult(): List<Article> {
    return this.map { row ->
        Article(
            row[Articles.id] ?: 0,
            row[Articles.authorId] ?: 0,
            row[Articles.title] ?: "",
            row[Articles.body] ?: "",
            row[Articles.description] ?: ""
        )
    }
}
fun selectArticleById(id: Int): Article {
    return DB.database.from(Articles).select().where { Articles.id eq id }.mapRowToResult().firstOrNull() ?: throw LocalRuntimeException(Exception.ARTICLE_NOT_FOUND)
}
fun selectAllArticles(): List<Article> {
    return DB.database.from(Articles).select().mapRowToResult()
}
fun updateArticle(article: Article) {
    DB.database.updateWithObject(Articles,article)
}
