package dev.psegerfast.warehouse.domain.model

typealias ProductId = Long

data class Product(
    val id: ProductId?,
    val name: String,
    val components: List<ProductComponent>,
    val price: Double? = null,
)
