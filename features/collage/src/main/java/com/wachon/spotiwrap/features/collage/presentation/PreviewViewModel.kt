package com.wachon.spotiwrap.features.collage.presentation

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.features.artists.domain.GetUserTopGenresFromArtistsUseCase
import com.wachon.spotiwrap.features.collage.domain.GetAlbumsCoversUseCase
import com.wachon.spotiwrap.features.collage.domain.GetAlbumsUseCase
import com.wachon.spotiwrap.features.collage.domain.GetArtistsCoversUseCase
import com.wachon.spotiwrap.features.collage.domain.GetArtistsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PreviewViewModel(
    getArtists: GetArtistsUseCase,
    getAlbums: GetAlbumsUseCase,
    getArtistsCovers: GetArtistsCoversUseCase,
    getUserTopGenres: GetUserTopGenresFromArtistsUseCase,
    getAlbumsCovers: GetAlbumsCoversUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PreviewScreenState())
    val uiState: StateFlow<PreviewScreenState> = _uiState

    private val artistsShort = getArtists(timeRange = TopItemTimeRange.SHORT_TERM)
    private val artistsMedium = getArtists(timeRange = TopItemTimeRange.MEDIUM_TERM)
    private val artistsLong = getArtists(timeRange = TopItemTimeRange.LONG_TERM)

    private val tracksShort = getAlbums(timeRange = TopItemTimeRange.SHORT_TERM)
    private val tracksMedium = getAlbums(timeRange = TopItemTimeRange.MEDIUM_TERM)
    private val tracksLong = getAlbums(timeRange = TopItemTimeRange.LONG_TERM)

    private val genresShort = getUserTopGenres(artistsShort)
    private val genresMedium = getUserTopGenres(artistsMedium)
    private val genresLong = getUserTopGenres(artistsLong)

    private val short = combine(
        artistsShort,
        tracksShort,
        genresShort,
    ) { artistsShort, tracksShort, genresShort ->
        _uiState.update {
            it.copy(
                isLoading = false,
                artistsShort = artistsShort,
                tracksShort = tracksShort,
                genresShort = genresShort,
            )
        }
    }

    private val medium = combine(
        artistsMedium,
        tracksMedium,
        genresMedium,
    ) { artistsMedium, tracksMedium, genresMedium ->
        _uiState.update {
            it.copy(
                artistsMedium = artistsMedium,
                tracksMedium = tracksMedium,
                genresMedium = genresMedium,
            )
        }
    }

    private val long = combine(
        artistsLong,
        tracksLong,
        genresLong,
    ) { artistsLong, tracksLong, genresLong ->
        _uiState.update {
            it.copy(
                artistsLong = artistsLong,
                tracksLong = tracksLong,
                genresLong = genresLong,
            )
        }
    }

    init {
        viewModelScope.launch {
            short.collect {}
            medium.collect {}
            long.collect {}
        }
    }

    fun getPreviewBitmap() = _uiState.value.previewBitmap

    fun storePreviewBitmap(bitmap: Bitmap) {
        if (!getPreviewBitmap().sameAs(bitmap)) {
            _uiState.update {
                it.copy(previewBitmap = bitmap)
            }
        }
    }
}