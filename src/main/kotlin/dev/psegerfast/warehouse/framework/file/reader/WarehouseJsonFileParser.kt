package dev.psegerfast.warehouse.framework.file.reader

import dev.psegerfast.warehouse.framework.file.dto.RawInventoryDto
import dev.psegerfast.warehouse.framework.file.dto.RawProductsDto
import kotlinx.serialization.json.Json
import java.lang.Exception

/**
 * Parses json text to their corresponding dtos
 * */
internal class WarehouseJsonFileParser(
    private val jsonReader: WarehouseJsonFileReader,
) {
    suspend fun readProducts(): Result<RawProductsDto> {
        val json = jsonReader
            .readProductsJson()
            .getOrElse {
                return Result.failure(Exception("Failed to read products.", it))
            }
        val parsed: RawProductsDto = try {
            Json.Default.decodeFromString(json)
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to parse products json file.", e))
        }
        return Result.success(parsed)
    }

    suspend fun readInventory(): Result<RawInventoryDto> {
        val json = jsonReader
            .readInventoryJson()
            .getOrElse {
                return Result.failure(Exception("Failed to read products.", it))
            }
        val parsed: RawInventoryDto = try {
            Json.Default.decodeFromString(json)
        } catch (e: Exception) {
            return Result.failure(Exception("Failed to parse products json file.", e))
        }
        return Result.success(parsed)
    }
}