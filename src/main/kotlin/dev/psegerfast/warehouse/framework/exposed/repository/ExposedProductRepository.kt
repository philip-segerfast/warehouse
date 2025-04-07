package dev.psegerfast.warehouse.framework.exposed.repository

import dev.psegerfast.warehouse.adapter.repository.ProductRepository
import dev.psegerfast.warehouse.domain.model.Product
import dev.psegerfast.warehouse.domain.model.ProductId
import dev.psegerfast.warehouse.framework.exposed.database.dto.ProductComponentEntity
import dev.psegerfast.warehouse.framework.exposed.database.dto.ProductEntity
import dev.psegerfast.warehouse.framework.exposed.database.mapper.toDomain
import dev.psegerfast.warehouse.framework.exposed.database.table.ProductComponentsTable
import dev.psegerfast.warehouse.util.ioTransaction
import org.jetbrains.exposed.dao.id.CompositeID

class ExposedProductRepository : ProductRepository {

    override suspend fun getProduct(id: ProductId): Product? = ioTransaction {
        ProductEntity.findById(id)?.toDomain()
    }

    override suspend fun getAllProducts(): List<Product> = ioTransaction {
        ProductEntity.all().map { it.toDomain() }
    }

    override suspend fun saveProduct(product: Product): Product = ioTransaction {
        // 1. Save the product to db and get an auto-generated ID
        val createdProductId = ProductEntity.new {
            this.name = product.name
            this.price = product.price
        }.id.value

        // 2. Use this ID to store product components
        product.components.forEach { productComponent ->
            val id = CompositeID {
                it[ProductComponentsTable.articleId] = productComponent.articleId
                it[ProductComponentsTable.productId] = createdProductId
            }
            ProductComponentEntity.new(id) {
                this.amountRequired = productComponent.amountRequired
            }
        }

        // 3. Get product with components
        ProductEntity[createdProductId].toDomain()
    }

    override suspend fun deleteProduct(id: ProductId): Boolean = ioTransaction {
        ProductEntity.findById(id)?.delete()?.let { true } ?: false
    }

    override suspend fun clear(): Int = ioTransaction {
        // Components will also be deleted because of ReferenceOption.CASCADE
        ProductEntity.all().onEach { it.delete() }.count().toInt()
    }

}
