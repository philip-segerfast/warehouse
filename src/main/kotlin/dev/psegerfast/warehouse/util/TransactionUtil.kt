package dev.psegerfast.warehouse.util

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.transaction

internal suspend fun <T> ioTransaction(block: () -> T): T {
    return withContext(Dispatchers.IO) {
        transaction {
            block()
        }
    }
}