package com.wachon.spotiwrap.features.presentation.track

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.artists.domain.GetArtistUseCase
import com.wachon.spotiwrap.features.artists.presentation.model.ArtistUI
import com.wachon.spotiwrap.features.artists.presentation.model.toUI
import com.wachon.spotiwrap.features.tracks.domain.GetAlbumUseCase
import com.wachon.spotiwrap.features.tracks.domain.GetTrackFeaturesUseCase
import com.wachon.spotiwrap.features.tracks.domain.GetTrackUseCase
import com.wachon.spotiwrap.features.tracks.presentation.model.AlbumUI
import com.wachon.spotiwrap.features.tracks.presentation.model.toUI
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class TrackViewModel(
    savedStateHandle: SavedStateHandle,
    dispatcherProvider: DispatcherProvider,
    getTrack: GetTrackUseCase,
    getTrackFeatures: GetTrackFeaturesUseCase,
    getAlbum: GetAlbumUseCase,
    getArtist: GetArtistUseCase,
) : ViewModel() {

    private val trackId: String = checkNotNull(savedStateHandle["id"])
    private val track = getTrack(id = trackId)
    private val features = getTrackFeatures(id = trackId)

    val uiState = combine(
        track,
        features
    ) { track, features ->

        var album: AlbumUI? = null
        val artists: MutableList<ArtistUI> = mutableListOf()

        getAlbum(id = track.albumId).collect {
            album = it.toUI()
        }

        track.artistsIds.forEachIndexed { index, id ->
            getArtist(id = id).collect {
                artists.add(index, it.toUI())
            }
        }

        TrackScreenState(
            loading = false,
            track = track.toUI(),
            features = features,
            album = album,
            artists = artists
        )
    }
        .flowOn(dispatcherProvider.background)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TrackScreenState()
        )

}