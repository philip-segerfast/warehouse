package dev.psegerfast.warehouse.usecase

import dev.psegerfast.warehouse.adapter.repository.ArticleRepository
import dev.psegerfast.warehouse.adapter.repository.ProductRepository
import dev.psegerfast.warehouse.fake.FakeArticleRepository
import dev.psegerfast.warehouse.fake.FakeProductRepository
import dev.psegerfast.warehouse.testdata.articleTestData
import dev.psegerfast.warehouse.testdata.productsTestData
import dev.psegerfast.warehouse.usecase.presenter.ProductPresenter
import dev.psegerfast.warehouse.usecase.viewmodel.product.ProductViewModel
import dev.psegerfast.warehouse.util.bulletList
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.result.shouldNotBeSuccess
import io.kotest.matchers.shouldBe

class ProductStockAndSellUseCases : FunSpec({

    lateinit var getProductStockUseCase: GetProductStockUseCase
    lateinit var sellProductUseCase: SellProductUseCase

    lateinit var productRepository: ProductRepository
    lateinit var articleRepository: ArticleRepository

    beforeEach {
        productRepository = FakeProductRepository(productsTestData)
        articleRepository = FakeArticleRepository(articleTestData)

        getProductStockUseCase = GetProductStockUseCase(
            productRepository = productRepository,
            articleRepository = articleRepository,
            presenter = ProductPresenter(),
        )

        sellProductUseCase = SellProductUseCase(
            articleRepository = articleRepository,
            productRepository = productRepository,
            presenter = ProductPresenter()
        )
    }

    test("test show product stock") {
        // Check product stock with test data
        val productStocks = getProductStockUseCase()

        val expectedStocks = listOf(
            ProductViewModel(id=1, name="Dining Chair", stock=2, price="20.0"),
            ProductViewModel(id=2, name="Dinning Table", stock=1, price="Contact sales for pricing")
        )

        productStocks shouldBe expectedStocks
    }

    test("test sell items and check stock") {
        sellProductUseCase(1).let { result ->
            result.shouldBeSuccess()
            result.getOrThrow().success shouldBe true
        }

        getProductStockUseCase().let { productStocks ->
            listOf(
                ProductViewModel(id=1, name="Dining Chair", stock=1, price="20.0"),
                ProductViewModel(id=2, name="Dinning Table", stock=1, price="Contact sales for pricing")
            ).let { expectedStocks ->
                productStocks shouldBe expectedStocks
            }
        }


        sellProductUseCase(1).let { result ->
            result.shouldBeSuccess()
            result.getOrThrow().success shouldBe true
        }

        getProductStockUseCase().let { productStocks ->
            listOf(
                ProductViewModel(id=1, name="Dining Chair", stock=0, price="20.0"),
                ProductViewModel(id=2, name="Dinning Table", stock=0, price="Contact sales for pricing")
            ).let { expectedStocks ->
                productStocks shouldBe expectedStocks
            }
        }

        sellProductUseCase(1).shouldNotBeSuccess()

        getProductStockUseCase().let { productStocks ->
            listOf(
                ProductViewModel(id=1, name="Dining Chair", stock=0, price="20.0"),
                ProductViewModel(id=2, name="Dinning Table", stock=0, price="Contact sales for pricing")
            ).let { expectedStocks ->
                productStocks shouldBe expectedStocks
            }
        }
    }

})