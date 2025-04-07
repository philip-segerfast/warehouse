package dev.psegerfast.warehouse.usecase.presenter

import dev.psegerfast.warehouse.domain.model.Product
import dev.psegerfast.warehouse.domain.model.ProductId
import dev.psegerfast.warehouse.usecase.viewmodel.product.ProductViewModel
import dev.psegerfast.warehouse.usecase.viewmodel.product.SellProductResponseViewModel

class ProductPresenter {
    var availableProductsViewModel: List<ProductViewModel> = emptyList()
        private set

    var sellProductResponseViewModel: SellProductResponseViewModel? = null
        private set

    fun presentAvailableProducts(products: List<Product>, availableQuantities: Map<ProductId, Int>) {
        availableProductsViewModel = products.map { product ->
            ProductViewModel(
                id = product.id!!,
                name = product.name,
                stock = availableQuantities[product.id] ?: 0,
                price = product.price?.toString() ?: "Contact sales for pricing"
            )
        }
    }

    fun presentSellProductResult(success: Boolean, product: Product) {
        sellProductResponseViewModel = SellProductResponseViewModel(
            success = success,
            message = "Successfully sold 1 ${product.name}${product.price?.let { price -> " for $price money" } ?: ""}.",
        )
    }
}