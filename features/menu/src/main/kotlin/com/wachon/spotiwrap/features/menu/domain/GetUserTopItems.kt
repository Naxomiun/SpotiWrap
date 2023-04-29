package com.wachon.spotiwrap.features.menu.domain

import android.util.Log
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.menu.data.SpotifyService
import com.wachon.spotiwrap.features.menu.data.Top
import com.wachon.spotiwrap.features.menu.presentation.MenuCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach

interface GetUserTopItemsUseCase {
    operator fun invoke(type: MenuCategory, limit: Int?, offset: Int?, timeRange: String?): Flow<Top>
}

class GetUserTopItems(
    private val spotifyService: SpotifyService,
    private val dispatchers: DispatcherProvider
) : GetUserTopItemsUseCase {

    override fun invoke(type: MenuCategory, limit: Int?, offset: Int?, timeRange: String?): Flow<Top> {
        return spotifyService
            .getTop(
                type = type.name.lowercase(),
                limit = limit,
                offset = offset,
                timeRange = timeRange
            )
            .onEach { Log.d("GetUserTopItems", "invoke: $it") }
            .flowOn(dispatchers.background)
    }

}