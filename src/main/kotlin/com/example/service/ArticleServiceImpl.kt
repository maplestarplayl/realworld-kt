package com.example.service

import com.example.DB.dbQuery
import com.example.entity.Article
import com.example.entity.Articles
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ArticleService {
    private fun resultRowToArticle(row: ResultRow) = Article(
        id = row[Articles.id],
        authorId = row[Articles.authorId],
        title = row[Articles.title],
        body = row[Articles.body],
        description = row[Articles.description]
    )
    suspend fun allArticles(): List<Article> = dbQuery {
        Articles.selectAll().map { resultRowToArticle(it) }
    }

    suspend fun getrticleById(id: Int): Article? = dbQuery {
        Articles
            .select { Articles.id.eq(id) }
            .mapNotNull { resultRowToArticle(it) }
            .singleOrNull() ?: throw IllegalArgumentException("No article found for id: $id")
    }

    suspend fun addNewArticle(title: String, body: String, authorId: Int, description: String): Article? = dbQuery {
        val insertStatement = Articles.insert {
            it[Articles.title] = title
            it[Articles.body] = body
            it[Articles.authorId] = 1
            it[Articles.description] = "description"
        }
        insertStatement.resultedValues?.singleOrNull()?.let { resultRowToArticle(it) }
    }

    suspend fun editArticle(id: Int, title: String, body: String): Boolean = dbQuery {
        Articles.update({ Articles.id eq id }) {
            it[Articles.title] = title
            it[Articles.body] = body
        } > 0
    }

    suspend fun deleteArticle(id: Int): Boolean = dbQuery{
        Articles.deleteWhere { Articles.id eq id } > 0
    }
}