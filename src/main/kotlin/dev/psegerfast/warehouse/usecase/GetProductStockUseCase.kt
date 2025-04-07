package dev.psegerfast.warehouse.usecase

import dev.psegerfast.warehouse.adapter.repository.ArticleRepository
import dev.psegerfast.warehouse.adapter.repository.ProductRepository
import dev.psegerfast.warehouse.domain.model.Article
import dev.psegerfast.warehouse.domain.model.ArticleId
import dev.psegerfast.warehouse.domain.model.Product
import dev.psegerfast.warehouse.usecase.presenter.ProductPresenter
import dev.psegerfast.warehouse.usecase.viewmodel.product.ProductViewModel
import dev.psegerfast.warehouse.util.bulletList

/**
 * Get all products and quantity of each that is an available with the current inventory
 * */
class GetProductStockUseCase(
    private val productRepository: ProductRepository,
    private val articleRepository: ArticleRepository,
    private val presenter: ProductPresenter,
) {
    suspend operator fun invoke(): List<ProductViewModel> {
        val products: List<Product> = productRepository.getAllProducts()
        val articlesById: Map<ArticleId, Article> = articleRepository.getAllArticles().associateBy { it.id }

        articleRepository.getAllArticles()

        // Count how many products of each can be constructed using the available article stock
        val numberOfCraftableProducts: Map<Product, Int> = products.associateWith { product ->
            val articlesTimesRequirements: Map<ArticleId, Int> = product.components.associate { (articleId, amountRequired) ->
                // How many components can be built out of all articles in stock
                val componentsTimesRequirements = articlesById[articleId]?.availableStock?.let { stock -> stock / amountRequired } ?: 0
                articleId to componentsTimesRequirements
            }
            articlesTimesRequirements.minOfOrNull { it.value } ?: 0
        }

        presenter.presentAvailableProducts(
            products = numberOfCraftableProducts.keys.toList(),
            availableQuantities = numberOfCraftableProducts.map { it.key.id!! to it.value }.toMap()
        )

        return presenter.availableProductsViewModel
    }

}
