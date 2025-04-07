package dev.psegerfast.warehouse.framework.file.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class RawInventoryDto(
    val inventory: List<RawInventoryArticleStockDto>,
)
