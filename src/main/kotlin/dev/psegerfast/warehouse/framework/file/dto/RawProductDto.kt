package dev.psegerfast.warehouse.framework.file.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RawProductDto(
    val name: String,
    @SerialName("contain_articles")
    val components: List<RawProductComponentDto>,
    val price: Double? = null
)
