package com.wachon.spotiwrap.features.presentation.artist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.artists.domain.GetArtistUseCase
import com.wachon.spotiwrap.features.artists.presentation.model.toUI
import com.wachon.spotiwrap.features.tracks.domain.GetArtistAlbumsUseCase
import com.wachon.spotiwrap.features.tracks.domain.GetArtistRelatedUseCase
import com.wachon.spotiwrap.features.tracks.domain.GetArtistTopTracksUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class ArtistViewModel(
    savedStateHandle: SavedStateHandle,
    dispatcherProvider: DispatcherProvider,
    getArtist: GetArtistUseCase,
    getArtistAlbums: GetArtistAlbumsUseCase,
    getArtistTopTracks: GetArtistTopTracksUseCase,
    getArtistRelated: GetArtistRelatedUseCase
) : ViewModel() {

    private val artistId: String = checkNotNull(savedStateHandle["id"])
    private val artist = getArtist(id = artistId)
    private val albums = getArtistAlbums(id = artistId)
    private val topTracks = getArtistTopTracks(id = artistId)
    private val related = getArtistRelated(id = artistId)

    val uiState = combine(
        artist,
        albums,
        topTracks,
        related,
    ) { artist, albums, topTracks, related ->

        val isLoading = albums.isEmpty() || topTracks.isEmpty() || related.isEmpty()

        ArtistScreenState(
            loading = isLoading,
            artist = artist.toUI(),
            albums = albums,
            topTracks = topTracks,
            related = related
        )
    }
        .flowOn(dispatcherProvider.background)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ArtistScreenState()
        )
}