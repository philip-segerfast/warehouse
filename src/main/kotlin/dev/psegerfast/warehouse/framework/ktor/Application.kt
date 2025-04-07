package dev.psegerfast.warehouse.framework.ktor

import dev.psegerfast.warehouse.framework.ktor.config.configureContentNegotiation
import dev.psegerfast.warehouse.framework.ktor.config.configureDatabase
import dev.psegerfast.warehouse.framework.ktor.config.configureMonitoring
import dev.psegerfast.warehouse.framework.ktor.config.configureRouting
import dev.psegerfast.warehouse.framework.ktor.module.appModule
import dev.psegerfast.warehouse.usecase.GetProductStockUseCase
import dev.psegerfast.warehouse.usecase.RestoreDataUseCase
import dev.psegerfast.warehouse.usecase.SellProductUseCase
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    EngineMain.main(args)
}

@OptIn(DelicateCoroutinesApi::class)
fun Application.module() {
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }

    configureContentNegotiation()
    configureDatabase()
    configureMonitoring()
    configureRouting()
}
