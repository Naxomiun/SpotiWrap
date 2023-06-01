package com.wachon.spotiwrap.features.recommender.di

import com.wachon.spotiwrap.features.recommender.domain.GetGenres
import com.wachon.spotiwrap.features.recommender.domain.GetGenresUseCase
import com.wachon.spotiwrap.features.recommender.domain.GetRecommendations
import com.wachon.spotiwrap.features.recommender.domain.GetRecommendationsUseCase
import com.wachon.spotiwrap.features.recommender.domain.SearchArtist
import com.wachon.spotiwrap.features.recommender.domain.SearchArtistUseCase
import com.wachon.spotiwrap.features.recommender.domain.SearchTrack
import com.wachon.spotiwrap.features.recommender.domain.SearchTrackUseCase
import com.wachon.spotiwrap.features.recommender.presentation.RecommenderViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

val RecommenderModule = module {
    includes(
        RecommenderDataModule,
        RecommenderDomainModule,
        RecommenderPresentationModule
    )
}

private val RecommenderDataModule: Module
    get() = module {

    }

private val RecommenderDomainModule: Module
    get() = module {
        factory<GetGenresUseCase> { GetGenres(get(), get()) }
        factory<SearchArtistUseCase> { SearchArtist(get(), get()) }
        factory<SearchTrackUseCase> { SearchTrack(get(), get()) }
        factory<GetRecommendationsUseCase> { GetRecommendations(get(), get()) }
    }

private val RecommenderPresentationModule: Module
    get() = module {
        viewModelOf(::RecommenderViewModel)
    }
