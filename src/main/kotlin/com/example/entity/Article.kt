package com.example.entity

import com.example.config.DB
import com.example.enum.Exception
import com.example.exception.LocalRuntimeException
import com.example.util.mapRowToResult
import com.example.util.updateWithObject
import kotlinx.serialization.Serializable
import org.ktorm.dsl.QueryRowSet
import org.ktorm.dsl.eq
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.BaseTable
import org.ktorm.schema.int
import org.ktorm.schema.varchar

@Serializable
class Article(val id: Int,
              val authorId: Int,
              val title: String,
              val body: String,
              val description: String)
object Articles : BaseTable<Article>("article") {
    val id = int("id").primaryKey()
    val authorId = int("author_id")
    val title = varchar("title")
    val body = varchar("body",)
    val description = varchar("description")
    override fun doCreateEntity(row: QueryRowSet, withReferences: Boolean) = Article(
        id = row[id] ?: 0,
        authorId = row[authorId] ?: 0,
        title = row[title] ?: "",
        body = row[body] ?: "",
        description = row[description] ?: ""
    )
}

fun selectArticleById(id: Int): Article {
    //return DB.database.from(Articles).select().where { Articles.id eq id }.firstOrNull() ?: throw LocalRuntimeException(Exception.ARTICLE_NOT_FOUND)
    return DB.database.sequenceOf(Articles).find { Articles.id eq id } ?: throw LocalRuntimeException(Exception.ARTICLE_NOT_FOUND)
}
fun selectAllArticles(): List<Article> {
    return DB.database.from(Articles).select().mapRowToResult()
}
fun updateArticle(article: Article) {
    DB.database.updateWithObject(Articles,article)
}
