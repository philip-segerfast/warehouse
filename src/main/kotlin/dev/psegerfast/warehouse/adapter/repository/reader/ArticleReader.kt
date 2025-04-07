package dev.psegerfast.warehouse.adapter.repository.reader

import dev.psegerfast.warehouse.domain.model.Article

interface ArticleReader {

    suspend fun readArticles(): Result<List<Article>>

}
