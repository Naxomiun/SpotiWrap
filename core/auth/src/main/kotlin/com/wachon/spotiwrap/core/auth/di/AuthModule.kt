package com.wachon.spotiwrap.core.auth.di

import com.wachon.spotiwrap.core.auth.DefaultTokenDatasource
import com.wachon.spotiwrap.core.auth.DefaultTokenRepository
import com.wachon.spotiwrap.core.auth.TokenDatasource
import com.wachon.spotiwrap.core.auth.TokenRepository
import com.wachon.spotiwrap.core.auth.TokenService
import com.wachon.spotiwrap.core.auth.config.AuthConfigProvider
import com.wachon.spotiwrap.core.auth.config.DefaultAuthConfigProvider
import com.wachon.spotiwrap.core.auth.config.GetAuthConfig
import com.wachon.spotiwrap.core.auth.config.GetAuthConfigUseCase
import com.wachon.spotiwrap.core.auth.scopes.CheckScopesAreValid
import com.wachon.spotiwrap.core.auth.scopes.CheckScopesAreValidUseCase
import com.wachon.spotiwrap.core.auth.scopes.GetAuthScopes
import com.wachon.spotiwrap.core.auth.scopes.GetAuthScopesUseCase
import com.wachon.spotiwrap.core.auth.scopes.SaveAuthScopes
import com.wachon.spotiwrap.core.auth.scopes.SaveAuthScopesUseCase
import com.wachon.spotiwrap.core.auth.token.GetAndPersistAccessToken
import com.wachon.spotiwrap.core.auth.token.GetAndPersistAccessTokenUseCase
import com.wachon.spotiwrap.core.auth.token.GetToken
import com.wachon.spotiwrap.core.auth.token.GetTokenUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val AuthModule
    get() = module {
        single { TokenService(get(named("AuthClient"))) }

        singleOf(::DefaultTokenDatasource) bind TokenDatasource::class
        singleOf(::DefaultAuthConfigProvider) bind AuthConfigProvider::class
        singleOf(::DefaultTokenRepository) bind TokenRepository::class

        factoryOf(::GetToken) bind GetTokenUseCase::class
        factoryOf(::GetAuthConfig) bind GetAuthConfigUseCase::class
        factoryOf(::SaveAuthScopes) bind SaveAuthScopesUseCase::class
        factoryOf(::GetAuthScopes) bind GetAuthScopesUseCase::class
        factoryOf(::CheckScopesAreValid) bind CheckScopesAreValidUseCase::class
        factoryOf(::GetAndPersistAccessToken) bind GetAndPersistAccessTokenUseCase::class
    }