package dev.psegerfast.warehouse.framework.exposed.database.table

import dev.psegerfast.warehouse.domain.model.ArticleId
import org.jetbrains.exposed.dao.id.IdTable

object ArticlesTable : IdTable<ArticleId>() {
    override val id = long("id").entityId()
    val name = varchar("name", 255)
    val availableStock = integer("availableStock")

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}
