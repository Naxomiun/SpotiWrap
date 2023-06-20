package com.wachon.spotiwrap.core.persistence.di

import com.wachon.spotiwrap.core.persistence.sharedpreferences.DataProvider
import com.wachon.spotiwrap.core.persistence.sharedpreferences.SharedPreferencesProvider
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val PersistenceModule: Module
    get() = module {
        singleOf(::SharedPreferencesProvider) bind DataProvider::class
    }