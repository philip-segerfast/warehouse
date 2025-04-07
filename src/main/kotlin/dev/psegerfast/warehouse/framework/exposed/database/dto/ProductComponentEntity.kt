package dev.psegerfast.warehouse.framework.exposed.database.dto

import dev.psegerfast.warehouse.framework.exposed.database.table.ProductComponentsTable
import org.jetbrains.exposed.dao.CompositeEntity
import org.jetbrains.exposed.dao.CompositeEntityClass
import org.jetbrains.exposed.dao.id.CompositeID
import org.jetbrains.exposed.dao.id.EntityID

internal class ProductComponentEntity(id: EntityID<CompositeID>) : CompositeEntity(id) {
    companion object : CompositeEntityClass<ProductComponentEntity>(ProductComponentsTable)

    var articleId by ProductComponentsTable.articleId
    var productId by ProductComponentsTable.productId
    var amountRequired by ProductComponentsTable.amountRequired
}
