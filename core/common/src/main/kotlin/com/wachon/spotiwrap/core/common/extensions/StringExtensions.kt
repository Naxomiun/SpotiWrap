package com.wachon.spotiwrap.core.common.extensions

import com.wachon.spotiwrap.core.common.R
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange.LONG_TERM
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange.MEDIUM_TERM
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange.SHORT_TERM
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.common.model.TopItemType.ARTIST
import com.wachon.spotiwrap.core.common.model.TopItemType.ARTISTS
import com.wachon.spotiwrap.core.common.model.TopItemType.TRACK
import com.wachon.spotiwrap.core.common.model.TopItemType.TRACKS

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

fun TopItemType.toShow() = when (this) {
    TRACK -> R.string.track
    TRACKS -> R.string.tracks
    ARTIST -> R.string.artist
    ARTISTS -> R.string.artists
}

fun TopItemTimeRange.toShow() = this.name.replace("_", " ").capitalizeWords()

fun TopItemTimeRange.toTime() = when (this) {
    SHORT_TERM -> R.string.last_month
    MEDIUM_TERM -> R.string.last_6_months
    LONG_TERM -> R.string.lifetime
}