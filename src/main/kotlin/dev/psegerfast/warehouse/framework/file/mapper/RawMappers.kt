package dev.psegerfast.warehouse.framework.file.mapper

import dev.psegerfast.warehouse.domain.model.Article
import dev.psegerfast.warehouse.domain.model.Product
import dev.psegerfast.warehouse.domain.model.ProductComponent
import dev.psegerfast.warehouse.framework.file.dto.RawInventoryArticleStockDto
import dev.psegerfast.warehouse.framework.file.dto.RawInventoryDto
import dev.psegerfast.warehouse.framework.file.dto.RawProductComponentDto
import dev.psegerfast.warehouse.framework.file.dto.RawProductDto
import dev.psegerfast.warehouse.framework.file.dto.RawProductsDto

internal fun RawProductsDto.toDomain(): List<Product> {
    return this.products.map { it.toDomain() }
}

internal fun RawProductDto.toDomain(): Product = Product(
    id = null,
    name = this.name,
    components = this.components.map { it.toDomain() },
    price = this.price
)

internal fun RawProductComponentDto.toDomain(): ProductComponent = ProductComponent(
    articleId = this.articleId.toLong(),
    amountRequired = this.amountRequired
)

internal fun RawInventoryDto.toDomain(): List<Article> = this.inventory.map { it.toDomain() }

internal fun RawInventoryArticleStockDto.toDomain(): Article = Article(
    id = this.articleId.toLong(),
    name = this.name,
    availableStock = this.stock,
)
