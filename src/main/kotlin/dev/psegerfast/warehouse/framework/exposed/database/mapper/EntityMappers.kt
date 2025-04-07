package dev.psegerfast.warehouse.framework.exposed.database.mapper

import dev.psegerfast.warehouse.domain.model.Article
import dev.psegerfast.warehouse.domain.model.Product
import dev.psegerfast.warehouse.domain.model.ProductComponent
import dev.psegerfast.warehouse.framework.exposed.database.dto.ArticleEntity
import dev.psegerfast.warehouse.framework.exposed.database.dto.ProductComponentEntity
import dev.psegerfast.warehouse.framework.exposed.database.dto.ProductEntity

internal fun ArticleEntity.toDomain(): Article = Article(
    id = this.id.value,
    name = this.name,
    availableStock = this.availableStock,
)

internal fun ProductEntity.toDomain(): Product = Product(
    id = this.id.value,
    name = this.name,
    price = this.price,
    components = this.components.map { it.toDomain() }
)

internal fun ProductComponentEntity.toDomain(): ProductComponent = ProductComponent(
    articleId = this.articleId.value,
    amountRequired = this.amountRequired,
)
