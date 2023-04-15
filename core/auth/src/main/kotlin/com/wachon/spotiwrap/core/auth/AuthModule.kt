package com.wachon.spotiwrap.core.auth

import org.koin.dsl.module

val AuthModule = module {
    factory<GetTokenUseCase> { GetToken(get()) }
    factory<SaveTokenUseCase> { SaveToken(get()) }
}
