package com.wachon.spotiwrap.core.persistence.di

import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedDataProvider
import com.wachon.spotiwrap.core.persistence.encrypted.SharedPreferencesProvider
import org.koin.core.module.Module
import org.koin.dsl.module

val PersistenceModule: Module
    get() = module {
        single<EncryptedDataProvider> { SharedPreferencesProvider(get()) }
    }