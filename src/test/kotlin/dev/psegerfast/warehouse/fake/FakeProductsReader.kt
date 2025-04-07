package dev.psegerfast.warehouse.fake

import dev.psegerfast.warehouse.adapter.repository.reader.ProductsReader
import dev.psegerfast.warehouse.domain.model.Product
import dev.psegerfast.warehouse.testdata.productsTestData

class FakeProductsReader : ProductsReader {
    override suspend fun readProducts(): Result<List<Product>> {
        return Result.success(productsTestData)
    }
}
