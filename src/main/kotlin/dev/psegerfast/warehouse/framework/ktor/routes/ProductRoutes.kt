package dev.psegerfast.warehouse.framework.ktor.routes

import dev.psegerfast.warehouse.usecase.GetProductStockUseCase
import dev.psegerfast.warehouse.usecase.SellProductUseCase
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

fun Route.productRoutes() {
    val getProductStockUseCase: GetProductStockUseCase by inject()
    val sellProductUseCase: SellProductUseCase by inject()

    route("/products") {
        get("/inventory") {
            // Get number of available products.
            val products = getProductStockUseCase()
            call.respond(products)
        }

        post("/sell") {
            val sellRequest = call.receive<SellProductRequest>()
            sellProductUseCase(sellRequest.productId).onSuccess {
                call.respond(it)
            }.onFailure {
                call.respondText("Failed to sell product with ID ${sellRequest.productId}: ${it.message}")
            }
        }
    }
}

@Serializable
data class SellProductRequest(
    val productId: Long,
)
