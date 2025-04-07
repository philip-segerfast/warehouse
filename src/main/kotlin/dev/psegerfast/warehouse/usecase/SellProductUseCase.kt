package dev.psegerfast.warehouse.usecase

import dev.psegerfast.warehouse.adapter.repository.ArticleRepository
import dev.psegerfast.warehouse.adapter.repository.ProductRepository
import dev.psegerfast.warehouse.domain.model.ProductId
import dev.psegerfast.warehouse.usecase.presenter.ProductPresenter
import dev.psegerfast.warehouse.usecase.viewmodel.product.SellProductResponseViewModel

class SellProductUseCase(
    private val articleRepository: ArticleRepository,
    private val productRepository: ProductRepository,
    private val presenter: ProductPresenter,
) {

    /**
     * @return the sold product
     * */
    suspend operator fun invoke(productId: ProductId): Result<SellProductResponseViewModel> {
        // Get current stock for product component articles
        val product = productRepository.getProduct(productId) ?: return Result.failure(Exception("Couldn't find product with id $productId"))
        val productComponents = product.components

        // Get current stock for articles
        val componentsByArticles = productComponents.associateWith { component ->
            articleRepository.getArticle(component.articleId)!!
        }

        // Ensure that there's enough stock for each article
        val inStock = componentsByArticles.all { (component, article) ->
            article.availableStock >= component.amountRequired
        }

        if(!inStock) return Result.failure(Exception("Product ${product.name} is of stock!"))

        // "Sell" product and remove components from stock
        componentsByArticles.forEach { (component, article) ->
            val newArticle = article.copy(
                availableStock = article.availableStock - component.amountRequired
            )
            articleRepository.updateArticle(newArticle)
        }

        presenter.presentSellProductResult(true, product)

        return Result.success(presenter.sellProductResponseViewModel!!)
    }

}
