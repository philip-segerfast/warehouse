package dev.psegerfast.warehouse.testdata

import dev.psegerfast.warehouse.domain.model.Article

val articleTestData = listOf(
    Article(id = 1, name = "leg", availableStock = 12),
    Article(id = 2, name = "screw", availableStock = 17),
    Article(id = 3, name = "seat", availableStock = 2),
    Article(id = 4, name = "table top", availableStock = 1)
)