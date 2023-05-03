package com.wachon.spotiwrap.core.persistence.di

import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedDataProvider
import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedSharedPreferencesProvider
import com.wachon.spotiwrap.core.persistence.sharedpreferences.DataProvider
import com.wachon.spotiwrap.core.persistence.sharedpreferences.SharedPreferencesProvider
import org.koin.core.module.Module
import org.koin.dsl.module

val PersistenceModule: Module
    get() = module {
        single<EncryptedDataProvider> { EncryptedSharedPreferencesProvider(get()) }
        single<DataProvider> { SharedPreferencesProvider(get()) }
    }