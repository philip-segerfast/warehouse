package dev.psegerfast.warehouse.framework.exposed.database.dto

import dev.psegerfast.warehouse.domain.model.ArticleId
import dev.psegerfast.warehouse.framework.exposed.database.table.ArticlesTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

internal class ArticleEntity(id: EntityID<ArticleId>) : LongEntity(id) {
    companion object : LongEntityClass<ArticleEntity>(ArticlesTable)

    var name by ArticlesTable.name
    var availableStock by ArticlesTable.availableStock
}
