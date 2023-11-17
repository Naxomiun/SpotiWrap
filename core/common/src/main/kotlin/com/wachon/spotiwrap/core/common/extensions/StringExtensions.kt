package com.wachon.spotiwrap.core.common.extensions

fun String.capitalizeFirst(): String {
    return this.lowercase().replaceFirstChar(Char::uppercaseChar)
}

fun String.capitalizeWords(): String {
    return this
        .split(' ')
        .joinToString(" ") {
            it.replaceFirstChar(Char::uppercaseChar)
        }
}
