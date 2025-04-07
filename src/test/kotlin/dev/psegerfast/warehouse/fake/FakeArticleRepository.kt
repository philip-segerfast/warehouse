package dev.psegerfast.warehouse.fake

import dev.psegerfast.warehouse.adapter.repository.ArticleRepository
import dev.psegerfast.warehouse.domain.model.Article
import dev.psegerfast.warehouse.domain.model.ArticleId

class FakeArticleRepository(
    initial: List<Article>? = null,
) : ArticleRepository {

    private val storage = mutableMapOf<ArticleId, Article>()

    init {
        initial?.let {
            storage.putAll(initial.associateBy { it.id })
        }
    }

    override suspend fun getArticle(id: ArticleId): Article? {
        return storage[id]
    }

    override suspend fun getAllArticles(): List<Article> {
        return storage.values.toList()
    }

    override suspend fun saveArticle(article: Article) {
        // Assume save always inserts a new article
        if (storage.containsKey(article.id)) {
            error("Article with id ${article.id} already exists.")
        }
        storage[article.id] = article
    }

    override suspend fun updateArticle(article: Article) {
        if (!storage.containsKey(article.id)) {
            error("Cannot update article with id ${article.id}: not found.")
        }
        storage[article.id] = article
    }

    override suspend fun deleteArticle(id: ArticleId) {
        storage.remove(id)
    }

    override suspend fun clear(): Int {
        val count = storage.size
        storage.clear()
        return count
    }
}
