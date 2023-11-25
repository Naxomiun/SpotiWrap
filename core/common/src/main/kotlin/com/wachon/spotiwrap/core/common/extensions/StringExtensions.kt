package com.wachon.spotiwrap.core.common.extensions

import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange.LONG_TERM
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange.MEDIUM_TERM
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange.SHORT_TERM
import com.wachon.spotiwrap.core.common.model.TopItemType

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

fun TopItemType.toShow() = this.name.capitalizeFirst()

fun TopItemTimeRange.toShow() = this.name.replace("_", " ").capitalizeWords()

fun TopItemTimeRange.toTime() = when (this) {
    SHORT_TERM -> "Last Month"
    MEDIUM_TERM -> "Last 6 months"
    LONG_TERM -> "Lifetime"
}