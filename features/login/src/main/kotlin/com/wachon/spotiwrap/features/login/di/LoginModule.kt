package com.wachon.spotiwrap.features.login.di

import com.wachon.spotiwrap.features.login.BuildConfig
import com.wachon.spotiwrap.features.login.data.AuthConfig
import com.wachon.spotiwrap.features.login.domain.GetAuthConfig
import com.wachon.spotiwrap.features.login.domain.SaveToken
import com.wachon.spotiwrap.features.login.domain.SaveTokenUseCase
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
        factory<SaveTokenUseCase> { SaveToken(get()) }
        factory {
            GetAuthConfig(
                AuthConfig(
                    clientId = BuildConfig.CLIENT_ID,
                    campaign = BuildConfig.CAMPAIGN_ID,
                    scopes = listOf("user-read-email", "user-read-private", "streaming"),
                    redirectUrl = "spotiwrap://auth"
                )
            )
        }
    }

private val LoginPresentationModule: Module
    get() = module {
        viewModelOf(::LoginViewModel)
    }