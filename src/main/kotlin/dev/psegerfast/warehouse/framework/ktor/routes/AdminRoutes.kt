package dev.psegerfast.warehouse.framework.ktor.routes

import dev.psegerfast.warehouse.usecase.RestoreDataUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import kotlin.getValue

fun Route.adminRoutes() {
    route("/admin") {
        post("/restore") {
            val restoreDataUseCase: RestoreDataUseCase by inject()

            restoreDataUseCase()
                .onSuccess {
                    call.respondText("Data restored.", status = HttpStatusCode.OK)
                }
                .onFailure {
                    val msg = "Failed to restore data: ${it.message ?: "Unknown reason"}"
                    System.err.println(msg)
                    it.printStackTrace()
                    call.respondText(msg, status = HttpStatusCode.InternalServerError)
                }
        }
    }
}