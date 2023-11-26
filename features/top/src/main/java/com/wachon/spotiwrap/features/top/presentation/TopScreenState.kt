package com.wachon.spotiwrap.features.top.presentation

import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange.SHORT_TERM
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.common.model.TopItemType.TRACKS
import com.wachon.spotiwrap.features.top.presentation.ui.TopItemUI

data class TopScreenState(

    val isLoading: Boolean = true,

    val timeSelected: TopItemTimeRange = SHORT_TERM,
    val typeSelected: TopItemType = TRACKS,
    val content: List<TopItemUI> = listOf(),
)