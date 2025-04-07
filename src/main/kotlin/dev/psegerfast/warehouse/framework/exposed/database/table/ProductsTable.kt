package dev.psegerfast.warehouse.framework.exposed.database.table

import dev.psegerfast.warehouse.domain.model.ProductId
import org.jetbrains.exposed.dao.id.IdTable

object ProductsTable : IdTable<ProductId>() {
    override val id = long("id")
        .autoIncrement()
        .entityId()
    val name = varchar("name", 255)
    val price = double("price").nullable()

    override val primaryKey = PrimaryKey(id)
}
