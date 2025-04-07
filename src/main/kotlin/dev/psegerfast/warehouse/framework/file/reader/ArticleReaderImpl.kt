package dev.psegerfast.warehouse.framework.file.reader

import dev.psegerfast.warehouse.adapter.repository.reader.ArticleReader
import dev.psegerfast.warehouse.framework.file.mapper.toDomain
import dev.psegerfast.warehouse.domain.model.Article

/** Reads inventory and returns domain objects */
internal class ArticleReaderImpl(
    private val warehouseJsonParser: WarehouseJsonFileParser,
) : ArticleReader {

    override suspend fun readArticles(): Result<List<Article>> {
        val inventoryDto = warehouseJsonParser
            .readInventory()
            .getOrElse { return Result.failure(Exception("Failed to read inventory articles", it)) }

        return Result.success(inventoryDto.toDomain())
    }

}
