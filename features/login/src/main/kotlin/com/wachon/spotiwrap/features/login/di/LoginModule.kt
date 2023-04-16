package com.wachon.spotiwrap.features.login.di

import com.wachon.spotiwrap.features.login.presentation.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

val LoginModule = module {
    includes(
        LoginDataModule,
        LoginDomainModule,
        LoginPresentationModule
    )
}

private val LoginDataModule: Module
    get() = module {

    }

private val LoginDomainModule: Module
    get() = module {

    }

private val LoginPresentationModule: Module
    get() = module {
        viewModelOf(::LoginViewModel)
    }