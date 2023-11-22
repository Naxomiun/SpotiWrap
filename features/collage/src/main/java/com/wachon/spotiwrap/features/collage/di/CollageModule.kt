package com.wachon.spotiwrap.features.collage.di

import com.wachon.spotiwrap.features.collage.domain.GetAlbums
import com.wachon.spotiwrap.features.collage.domain.GetAlbumsCovers
import com.wachon.spotiwrap.features.collage.domain.GetAlbumsCoversUseCase
import com.wachon.spotiwrap.features.collage.domain.GetAlbumsUseCase
import com.wachon.spotiwrap.features.collage.domain.GetArtists
import com.wachon.spotiwrap.features.collage.domain.GetArtistsCovers
import com.wachon.spotiwrap.features.collage.domain.GetArtistsCoversUseCase
import com.wachon.spotiwrap.features.collage.domain.GetArtistsUseCase
import com.wachon.spotiwrap.features.collage.presentation.PreviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

val PreviewModule = module {
    includes(
        CollageDataModule,
        CollageDomainModule,
        CollagePresentationModule
    )
}

private val CollageDataModule: Module
    get() = module {

    }

private val CollageDomainModule: Module
    get() = module {
        factory<GetArtistsUseCase> { GetArtists(get(), get()) }
        factory<GetArtistsCoversUseCase> { GetArtistsCovers() }
        factory<GetAlbumsUseCase> { GetAlbums(get(), get()) }
        factory<GetAlbumsCoversUseCase> { GetAlbumsCovers() }
    }

private val CollagePresentationModule: Module
    get() = module {
        viewModelOf(::PreviewViewModel)
    }