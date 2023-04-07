package com.wachon.spotiwrap.core.data.di

import com.wachon.spotiwrap.core.data.persistence.encrypted.EncryptedDataProvider
import com.wachon.spotiwrap.core.data.persistence.encrypted.SharedPreferencesProvider
import org.koin.core.module.Module
import org.koin.dsl.module

val DataModule = module {
    includes(
        PersistanceModule
    )
}

private val PersistanceModule: Module get() = module {
    single<EncryptedDataProvider> { SharedPreferencesProvider(get()) }
}