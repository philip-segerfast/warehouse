package dev.psegerfast.warehouse.framework.file.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RawProductComponentDto(
    @SerialName("art_id")
    val articleId: Int,
    @SerialName("amount_of")
    val amountRequired: Int,
)
