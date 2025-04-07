package dev.psegerfast.warehouse.framework.ktor.config

import dev.psegerfast.warehouse.framework.ktor.routes.adminRoutes
import dev.psegerfast.warehouse.framework.ktor.routes.productRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        adminRoutes()
        productRoutes()
    }
}
