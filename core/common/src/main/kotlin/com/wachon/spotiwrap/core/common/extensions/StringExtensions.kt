package com.wachon.spotiwrap.core.common.extensions

fun String.capitalizeWords(): String {
    return this
        .split(' ')
        .joinToString(" ") {
            it.replaceFirstChar(Char::uppercaseChar)
        }
}
