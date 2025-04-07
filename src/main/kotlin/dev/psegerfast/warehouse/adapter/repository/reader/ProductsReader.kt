package dev.psegerfast.warehouse.adapter.repository.reader

import dev.psegerfast.warehouse.domain.model.Product

interface ProductsReader {

    /** Read initial products from a source (JSON file). These products won't initially have an ID. */
    suspend fun readProducts(): Result<List<Product>>

}
