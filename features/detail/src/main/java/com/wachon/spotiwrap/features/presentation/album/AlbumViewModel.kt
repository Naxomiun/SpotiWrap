package com.wachon.spotiwrap.features.presentation.album

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.artists.domain.GetArtistUseCase
import com.wachon.spotiwrap.features.tracks.domain.GetAlbumTracksUseCase
import com.wachon.spotiwrap.features.tracks.domain.GetAlbumUseCase
import com.wachon.spotiwrap.features.tracks.presentation.model.toUI
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class AlbumViewModel(
    savedStateHandle: SavedStateHandle,
    dispatcherProvider: DispatcherProvider,
    getAlbum: GetAlbumUseCase,
    getAlbumTracks: GetAlbumTracksUseCase,
    getArtist: GetArtistUseCase
) : ViewModel() {

    private val albumId: String = checkNotNull(savedStateHandle["id"])
    private val album = getAlbum(id = albumId)
    private val tracks = getAlbumTracks(id = albumId)

    val uiState = combine(
        album,
        tracks
    ) { album, tracks ->

        val isLoading = tracks.isEmpty()

        album.artists.forEachIndexed { index, artistModel ->
            getArtist(id = artistModel.id).collect {
                album.artists[index].imageUrl = it.imageUrl
            }
        }

        AlbumScreenState(
            loading = isLoading,
            album = album.toUI(),
            tracks = tracks
        )
    }
        .flowOn(dispatcherProvider.background)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = AlbumScreenState()
        )
}