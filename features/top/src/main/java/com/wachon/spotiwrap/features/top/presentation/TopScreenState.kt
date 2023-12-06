package com.wachon.spotiwrap.features.top.presentation

import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange.SHORT_TERM
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.common.model.TopItemType.ARTISTS
import com.wachon.spotiwrap.core.common.model.TopItemType.TRACKS
import com.wachon.spotiwrap.core.design.ui.TopItemUI

data class TopScreenState(

    val isLoading: Boolean = true,

    val types: List<TopItemType> = listOf(TRACKS, ARTISTS),
    val times: List<TopItemTimeRange> = TopItemTimeRange.entries,

    var timeSelected: TopItemTimeRange = SHORT_TERM,
    var typeSelected: TopItemType = TRACKS,

    var content: List<TopItemUI> = listOf(),

    val tracksShort: List<TopItemUI> = listOf(),
    val tracksMedium: List<TopItemUI> = listOf(),
    val tracksLong: List<TopItemUI> = listOf(),
    val artistsShort: List<TopItemUI> = listOf(),
    val artistsMedium: List<TopItemUI> = listOf(),
    val artistsLong: List<TopItemUI> = listOf(),
)