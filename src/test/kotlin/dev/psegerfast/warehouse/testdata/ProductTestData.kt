package dev.psegerfast.warehouse.testdata

import dev.psegerfast.warehouse.domain.model.Product
import dev.psegerfast.warehouse.domain.model.ProductComponent

val productsTestData = listOf(
    Product(
        id = 1,
        name = "Dining Chair",
        components = listOf(
            ProductComponent(articleId = 1, amountRequired = 4),
            ProductComponent(articleId = 2, amountRequired = 8),
            ProductComponent(articleId = 3, amountRequired = 1)
        ),
        price = 20.0
    ),
    Product(
        id = 2,
        name = "Dinning Table",
        components = listOf(
            ProductComponent(articleId = 1, amountRequired = 4),
            ProductComponent(articleId = 2, amountRequired = 8),
            ProductComponent(articleId = 4, amountRequired = 1)
        ),
        price = null
    )
)