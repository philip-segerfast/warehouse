package dev.psegerfast.warehouse.usecase

import dev.psegerfast.warehouse.adapter.repository.ArticleRepository
import dev.psegerfast.warehouse.adapter.repository.ProductRepository
import dev.psegerfast.warehouse.adapter.repository.reader.ArticleReader
import dev.psegerfast.warehouse.adapter.repository.reader.ProductsReader
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext

class RestoreDataUseCase(
    private val productReader: ProductsReader,
    private val articleReader: ArticleReader,
    private val productRepository: ProductRepository,
    private val articleRepository: ArticleRepository,
) {
    suspend operator fun invoke(): Result<Unit> = runCatching {
        val products = productReader.readProducts().getOrThrow()
        val articles = articleReader.readArticles().getOrThrow()

        // Make sure these operations are fully executed even when job is cancelled.
        withContext(NonCancellable) {
            productRepository.clear()
            articleRepository.clear()

            // Saving articles
            articles.forEach { article ->
                articleRepository.saveArticle(article)
            }

            // Saving products
            products.forEach { product ->
                productRepository.saveProduct(product)
            }
        }
    }
}