package dev.psegerfast.warehouse.framework.exposed.database.dto

import dev.psegerfast.warehouse.domain.model.ProductId
import dev.psegerfast.warehouse.framework.exposed.database.table.ProductComponentsTable
import dev.psegerfast.warehouse.framework.exposed.database.table.ProductsTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

internal class ProductEntity(id: EntityID<ProductId>) : Entity<ProductId>(id) {
    companion object : EntityClass<ProductId, ProductEntity>(ProductsTable)

    var name by ProductsTable.name
    var price by ProductsTable.price
    val components by ProductComponentEntity referrersOn ProductComponentsTable.productId
}
