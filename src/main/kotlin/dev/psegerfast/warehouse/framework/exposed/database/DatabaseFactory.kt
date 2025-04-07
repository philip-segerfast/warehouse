package dev.psegerfast.warehouse.framework.exposed.database

import dev.psegerfast.warehouse.framework.exposed.database.table.ArticlesTable
import dev.psegerfast.warehouse.framework.exposed.database.table.ProductComponentsTable
import dev.psegerfast.warehouse.framework.exposed.database.table.ProductsTable
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    private var initialized = false

    fun init(config: ApplicationConfig) {
        // Only initialize once or else it will create leaks.
        if(initialized) return

        val driverName = config.property("ktor.database.driver").getString()
        val jdbcURL = config.property("ktor.database.url").getString()
        val username = config.property("ktor.database.user").getString() // Provide a default if needed
        val password = config.property("ktor.database.password").getString()

        Database.connect(
            url = jdbcURL,
            driver = driverName,
            user = username,
            password = password,
        )

        transaction {
            addLogger(StdOutSqlLogger)

            // Create tables if they don't already exist
            SchemaUtils.create(ProductsTable, ArticlesTable, ProductComponentsTable)
        }

        initialized = true
    }

}
