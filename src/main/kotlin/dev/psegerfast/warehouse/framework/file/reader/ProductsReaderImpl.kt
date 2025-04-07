package dev.psegerfast.warehouse.framework.file.reader

import dev.psegerfast.warehouse.adapter.repository.reader.ProductsReader
import dev.psegerfast.warehouse.framework.file.mapper.toDomain
import dev.psegerfast.warehouse.domain.model.Product

/** Reads product dtos and returns domain objects */
internal class ProductsReaderImpl(
    private val warehouseJsonReader: WarehouseJsonFileParser,
) : ProductsReader {

    override suspend fun readProducts(): Result<List<Product>> {
        val productsDto = warehouseJsonReader
            .readProducts()
            .getOrElse { return Result.failure(Exception("Failed to read products", it)) }

        return Result.success(productsDto.toDomain())
    }
}
