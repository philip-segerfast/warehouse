package dev.psegerfast.warehouse.fake

import dev.psegerfast.warehouse.adapter.repository.reader.ArticleReader
import dev.psegerfast.warehouse.domain.model.Article
import dev.psegerfast.warehouse.testdata.articleTestData

class FakeArticleReader : ArticleReader {

    override suspend fun readArticles(): Result<List<Article>> {
        return Result.success(articleTestData)
    }
}
