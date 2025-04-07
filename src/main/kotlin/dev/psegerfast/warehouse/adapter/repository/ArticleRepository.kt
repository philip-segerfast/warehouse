package dev.psegerfast.warehouse.adapter.repository

import dev.psegerfast.warehouse.domain.model.Article
import dev.psegerfast.warehouse.domain.model.ArticleId

interface ArticleRepository {

    suspend fun getArticle(id: ArticleId): Article?

    suspend fun getAllArticles(): List<Article>

    suspend fun saveArticle(article: Article)

    suspend fun updateArticle(article: Article)

    suspend fun deleteArticle(id: ArticleId)

    /** Clears table */
    suspend fun clear(): Int
}
