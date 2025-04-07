package dev.psegerfast.warehouse.framework.exposed.repository

import dev.psegerfast.warehouse.adapter.repository.ArticleRepository
import dev.psegerfast.warehouse.domain.model.Article
import dev.psegerfast.warehouse.domain.model.ArticleId
import dev.psegerfast.warehouse.framework.exposed.database.dto.ArticleEntity
import dev.psegerfast.warehouse.framework.exposed.database.mapper.toDomain
import dev.psegerfast.warehouse.util.ioTransaction

class ExposedArticleRepository : ArticleRepository {

    override suspend fun getArticle(id: ArticleId): Article? = ioTransaction {
        ArticleEntity.findById(id)?.toDomain()
    }

    override suspend fun getAllArticles(): List<Article> = ioTransaction {
        ArticleEntity.all().map { it.toDomain() }
    }

    override suspend fun saveArticle(article: Article): Unit = ioTransaction {
        ArticleEntity.new(article.id) {
            this.availableStock = article.availableStock
            this.name = article.name
        }
    }

    override suspend fun updateArticle(article: Article): Unit = ioTransaction {
        ArticleEntity.findByIdAndUpdate(article.id) {
            it.name = article.name
            it.availableStock = article.availableStock
        }
    }

    override suspend fun deleteArticle(id: ArticleId): Unit = ioTransaction {
        ArticleEntity.findById(id)?.delete()
    }

    override suspend fun clear(): Int = ioTransaction {
        ArticleEntity.all().onEach { it.delete() }.count().toInt()
    }

}
