package dev.psegerfast.warehouse.framework.file.reader

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.charset.Charset

/** Reads json files from disk and provides the text contents */
internal class WarehouseJsonFileReader {
    private val resourcesPath = "json"
    private val productsFilePath = "$resourcesPath/products.json"
    private val inventoryFilePath = "$resourcesPath/inventory.json"

    private suspend fun readJson(path: String): Result<String> {
        val classLoader = Thread.currentThread().contextClassLoader
        val inputStream = classLoader.getResourceAsStream(path)

        if(inputStream == null) {
            return Result.failure(kotlinx.io.files.FileNotFoundException("Couldn't find file with path \"$path\""))
        }

        val jsonContent = withContext(Dispatchers.IO) {
            inputStream
                .bufferedReader(Charset.defaultCharset())
                .use { it.readText() }
        }

        return Result.success(jsonContent)
    }

    suspend fun readProductsJson(): Result<String> = readJson(productsFilePath)

    suspend fun readInventoryJson(): Result<String> = readJson(inventoryFilePath)

}