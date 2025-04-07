package dev.psegerfast.warehouse.usecase.viewmodel.product

import kotlinx.serialization.Serializable

@Serializable
data class SellProductResponseViewModel(
    val success: Boolean,
    val message: String
)