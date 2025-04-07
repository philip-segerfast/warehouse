package dev.psegerfast.warehouse.fake

import dev.psegerfast.warehouse.adapter.repository.ProductRepository
import dev.psegerfast.warehouse.domain.model.Product
import dev.psegerfast.warehouse.domain.model.ProductId

class FakeProductRepository(
    initial: List<Product>? = null,
) : ProductRepository {

    private val storage = mutableMapOf<ProductId, Product>()
    private var nextId = 1L

    init {
        initial?.let {
            storage.putAll(initial.associateBy { it.id!! })
        }
    }

    override suspend fun getProduct(id: ProductId): Product? {
        return storage[id]
    }

    override suspend fun getAllProducts(): List<Product> {
        return storage.values.toList()
    }

    override suspend fun saveProduct(product: Product): Product {
        val assignedId = product.id ?: nextId++
        val savedProduct = product.copy(id = assignedId)
        storage[assignedId] = savedProduct
        return savedProduct
    }

    override suspend fun deleteProduct(id: ProductId): Boolean {
        return storage.remove(id) != null
    }

    override suspend fun clear(): Int {
        val count = storage.size
        storage.clear()
        return count
    }
}
