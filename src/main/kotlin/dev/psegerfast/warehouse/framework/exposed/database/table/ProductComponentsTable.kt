package dev.psegerfast.warehouse.framework.exposed.database.table

import org.jetbrains.exposed.dao.id.CompositeIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object ProductComponentsTable : CompositeIdTable() {
    // When product/article is deleted, delete this row too.
    val productId = reference("productId", ProductsTable, onDelete = ReferenceOption.CASCADE)
    val articleId = reference("article", ArticlesTable, onDelete = ReferenceOption.CASCADE)
    val amountRequired = integer("amountRequired")

    init {
        addIdColumn(productId)
        addIdColumn(articleId)
    }

    override val primaryKey = PrimaryKey(productId, articleId)
}
