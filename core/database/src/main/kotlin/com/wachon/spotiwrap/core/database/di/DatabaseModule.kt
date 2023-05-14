package com.wachon.spotiwrap.core.database.di

import androidx.room.Room
import com.wachon.spotiwrap.core.database.datasource.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val DatabaseModule
    get() = module {
        single {
            Room.databaseBuilder(
                androidApplication(),
                AppDatabase::class.java,
                "app_database"
            )
                .fallbackToDestructiveMigration()
                .build()
        }

        factory { get<AppDatabase>().profileDao() }
        factory { get<AppDatabase>().trackDao() }
        factory { get<AppDatabase>().artistDao() }
    }