package com.wachon.spotiwrap.features.tracks.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.TrackFeaturesModel
import com.wachon.spotiwrap.data.repository.TracksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetTrackFeaturesUseCase {
    operator fun invoke(
        id: String
    ): Flow<TrackFeaturesModel>
}

class GetTrackFeatures(
    private val tracksRepository: TracksRepository,
    private val dispatchers: DispatcherProvider
) : GetTrackFeaturesUseCase {


    override fun invoke(id: String): Flow<TrackFeaturesModel> {
        return tracksRepository
            .getTrackFeatures(
                id = id
            )
            .flowOn(dispatchers.background)
    }

}