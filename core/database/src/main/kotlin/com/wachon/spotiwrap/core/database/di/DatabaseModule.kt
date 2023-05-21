package com.wachon.spotiwrap.core.database.di

import androidx.room.Room
import com.wachon.spotiwrap.core.database.datasource.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val DatabaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().profileDao() }
    single { get<AppDatabase>().trackDao() }
    single { get<AppDatabase>().artistDao() }
    single { get<AppDatabase>().genreDao() }
}