package dev.psegerfast.warehouse.usecase.viewmodel.product

import kotlinx.serialization.Serializable

@Serializable
data class ProductViewModel(
    val id: Long,
    val name: String,
    val stock: Int,
    val price: String,
)