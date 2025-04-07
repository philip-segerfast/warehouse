package dev.psegerfast.warehouse.domain.model

data class ProductComponent(
    val articleId: ArticleId,
    val amountRequired: Int,
)
