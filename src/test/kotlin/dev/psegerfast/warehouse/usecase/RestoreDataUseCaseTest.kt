package dev.psegerfast.warehouse.usecase

import dev.psegerfast.warehouse.fake.FakeArticleRepository
import dev.psegerfast.warehouse.fake.FakeArticleReader
import dev.psegerfast.warehouse.fake.FakeProductRepository
import dev.psegerfast.warehouse.fake.FakeProductsReader
import dev.psegerfast.warehouse.adapter.repository.ArticleRepository
import dev.psegerfast.warehouse.adapter.repository.ProductRepository
import dev.psegerfast.warehouse.adapter.repository.reader.ArticleReader
import dev.psegerfast.warehouse.adapter.repository.reader.ProductsReader
import dev.psegerfast.warehouse.testdata.articleTestData
import dev.psegerfast.warehouse.testdata.productsTestData
import io.kotest.core.spec.style.FunSpec
import io.kotest.engine.runBlocking
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe

class RestoreDataUseCaseTest : FunSpec({
    lateinit var articleRepository: ArticleRepository
    lateinit var productRepository: ProductRepository

    lateinit var productReader: ProductsReader
    lateinit var articleReader: ArticleReader

    lateinit var restoreDataUseCase: RestoreDataUseCase

    beforeEach {
        articleRepository = FakeArticleRepository()
        productRepository = FakeProductRepository()
        articleReader = FakeArticleReader()
        productReader = FakeProductsReader()

        restoreDataUseCase = RestoreDataUseCase(
            productReader = productReader,
            articleReader = articleReader,
            productRepository = productRepository,
            articleRepository = articleRepository,
        )
    }

    test("Test that restoreDataUseCase populates the repositories") {
        articleRepository.getAllArticles().shouldBeEmpty()
        productRepository.getAllProducts().shouldBeEmpty()

        val result = runBlocking { restoreDataUseCase() }
        result.isSuccess shouldBe true

        // Note - I could've used Mockk to test more detailed interactions but this would take more time.

        // Ensure that all articles are unique and that the number of articles matches the test data.
        // Note - this will not check detailed contents of each item which should be done in production.
        articleRepository.getAllArticles().distinct().count() shouldBe articleTestData.count()
        // Same as above but for products
        productRepository.getAllProducts().distinct().count() shouldBe productsTestData.count()
    }

})