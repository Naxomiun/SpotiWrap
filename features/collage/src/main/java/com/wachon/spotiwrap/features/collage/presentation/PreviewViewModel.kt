package com.wachon.spotiwrap.features.collage.presentation

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.features.collage.domain.GetAlbumsCoversUseCase
import com.wachon.spotiwrap.features.collage.domain.GetAlbumsUseCase
import com.wachon.spotiwrap.features.collage.domain.GetArtistsCoversUseCase
import com.wachon.spotiwrap.features.collage.domain.GetArtistsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PreviewViewModel(
    private val getArtists: GetArtistsUseCase,
    private val getAlbums: GetAlbumsUseCase,
    private val getArtistsCovers: GetArtistsCoversUseCase,
    private val getAlbumsCovers: GetAlbumsCoversUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PreviewScreenState())
    val uiState: StateFlow<PreviewScreenState> = _uiState

    init {
        getArtistsList()
        getAlbumList()
    }

    private fun getArtistsList() = viewModelScope.launch {
        getArtists(
            timeRange = _uiState.value.time[_uiState.value.timeIndex]
        ).collect { artists ->
            _uiState.update {
                it.copy(
                    isLoading = false,
                    artists = artists,
                    artistsCovers = getArtistsCovers(artists),
                )
            }
        }
    }

    private fun getAlbumList() = viewModelScope.launch {
        getAlbums(
            timeRange = _uiState.value.time[_uiState.value.timeIndex]
        ).collect { albums ->
            _uiState.update {
                it.copy(
                    isLoading = false,
                    albums = albums,
                    albumsCovers = getAlbumsCovers(albums),
                )
            }
        }
    }

    fun changeTimeIndex(index: Int) {
        _uiState.update {
            it.copy(timeIndex = index)
        }
        getArtistsList()
        getAlbumList()
    }

    fun changeOptionIndex(index: Int) {
        _uiState.update {
            it.copy(optionIndex = index)
        }
    }

    fun changeTypeIndex(index: Int) {
        _uiState.update {
            it.copy(typeIndex = index)
        }
    }

    fun changeSizeIndex(index: Int) {
        _uiState.update {
            it.copy(sizeIndex = index)
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