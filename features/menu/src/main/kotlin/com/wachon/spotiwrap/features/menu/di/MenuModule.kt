package com.wachon.spotiwrap.features.menu.di

import com.wachon.spotiwrap.features.menu.presentation.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

val MenuModule = module {
    includes(
        MenuDataModule,
        MenuDomainModule,
        MenuPresentationModule
    )
}

private val MenuDataModule: Module
    get() = module {

    }

private val MenuDomainModule: Module
    get() = module {

    }

private val MenuPresentationModule: Module
    get() = module {
        viewModelOf(::MenuViewModel)
    }
