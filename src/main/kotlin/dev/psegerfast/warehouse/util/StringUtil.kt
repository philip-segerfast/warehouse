package dev.psegerfast.warehouse.util

internal fun Collection<*>.bulletList(): String = joinToString(
    separator = System.lineSeparator(),
    transform = { "• $it" }
)