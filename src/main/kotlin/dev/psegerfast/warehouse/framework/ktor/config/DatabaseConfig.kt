package dev.psegerfast.warehouse.framework.ktor.config

import dev.psegerfast.warehouse.framework.exposed.database.DatabaseFactory
import io.ktor.server.application.Application

fun Application.configureDatabase() {
    DatabaseFactory.init(environment.config)
}