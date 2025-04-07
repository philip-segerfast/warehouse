package dev.psegerfast.warehouse.framework.ktor.module

import dev.psegerfast.warehouse.usecase.presenter.ProductPresenter
import dev.psegerfast.warehouse.adapter.repository.ArticleRepository
import dev.psegerfast.warehouse.adapter.repository.ProductRepository
import dev.psegerfast.warehouse.framework.exposed.repository.ExposedArticleRepository
import dev.psegerfast.warehouse.framework.exposed.repository.ExposedProductRepository
import dev.psegerfast.warehouse.framework.file.reader.ArticleReaderImpl
import dev.psegerfast.warehouse.framework.file.reader.ProductsReaderImpl
import dev.psegerfast.warehouse.adapter.repository.reader.ArticleReader
import dev.psegerfast.warehouse.adapter.repository.reader.ProductsReader
import dev.psegerfast.warehouse.framework.file.reader.WarehouseJsonFileParser
import dev.psegerfast.warehouse.framework.file.reader.WarehouseJsonFileReader
import dev.psegerfast.warehouse.usecase.GetProductStockUseCase
import dev.psegerfast.warehouse.usecase.RestoreDataUseCase
import dev.psegerfast.warehouse.usecase.SellProductUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    // Readers
    factoryOf(::WarehouseJsonFileReader)
    factoryOf(::WarehouseJsonFileParser)
    factoryOf(::ArticleReaderImpl).bind<ArticleReader>()
    factoryOf(::ProductsReaderImpl).bind<ProductsReader>()

    // Repositories
    factoryOf(::ExposedProductRepository).bind<ProductRepository>()
    factoryOf(::ExposedArticleRepository).bind<ArticleRepository>()

    // Presenters
    factoryOf(::ProductPresenter)

    // Use cases
    factoryOf(::GetProductStockUseCase)
    factoryOf(::RestoreDataUseCase)
    factoryOf(::SellProductUseCase)
}
