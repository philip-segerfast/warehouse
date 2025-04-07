package dev.psegerfast.warehouse.adapter.repository

import dev.psegerfast.warehouse.domain.model.Product
import dev.psegerfast.warehouse.domain.model.ProductId

interface ProductRepository {

    suspend fun getProduct(id: ProductId): Product?

    suspend fun getAllProducts(): List<Product>

    suspend fun saveProduct(product: Product): Product

    suspend fun deleteProduct(id: ProductId): Boolean

    /** @return number of cleared items */
    suspend fun clear(): Int

}
