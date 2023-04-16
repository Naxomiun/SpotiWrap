package com.wachon.spotiwrap.core.auth

import com.wachon.spotiwrap.core.auth.scopes.AuthConfigProvider
import com.wachon.spotiwrap.core.auth.scopes.DefaultAuthConfigProvider
import com.wachon.spotiwrap.core.auth.scopes.GetAuthConfig
import com.wachon.spotiwrap.core.auth.scopes.GetAuthConfigUseCase
import com.wachon.spotiwrap.core.auth.token.GetToken
import com.wachon.spotiwrap.core.auth.token.GetTokenUseCase
import com.wachon.spotiwrap.core.auth.token.SaveToken
import com.wachon.spotiwrap.core.auth.token.SaveTokenUseCase
import org.koin.dsl.module

val AuthModule = module {
    single<AuthConfigProvider> { DefaultAuthConfigProvider() }
    factory<GetTokenUseCase> { GetToken(get()) }
    factory<SaveTokenUseCase> { SaveToken(get()) }
    factory<GetAuthConfigUseCase> { GetAuthConfig(get()) }
}
