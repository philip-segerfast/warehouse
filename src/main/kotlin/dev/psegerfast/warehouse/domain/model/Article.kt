package dev.psegerfast.warehouse.domain.model

typealias ArticleId = Long

data class Article(
    val id: ArticleId,
    val name: String,
    val availableStock: Int,
)
