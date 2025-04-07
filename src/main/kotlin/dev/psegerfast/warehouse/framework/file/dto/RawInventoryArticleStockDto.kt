package dev.psegerfast.warehouse.framework.file.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RawInventoryArticleStockDto(
    @SerialName("art_id")
    val articleId: Int,
    val name: String,
    val stock: Int,
)
