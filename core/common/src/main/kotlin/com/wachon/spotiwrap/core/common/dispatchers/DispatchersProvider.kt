package com.wachon.spotiwrap.core.common.dispatchers

import Background
import com.wachon.spotiwrap.core.common.dispatchers.BackgroundDispatcher.Background
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {
    val background: CoroutineDispatcher
    val io: CoroutineDispatcher
    val mainImmediate: CoroutineDispatcher
    val default: CoroutineDispatcher
}

class DefaultDispatcherProvider : DispatcherProvider {
    override val io = Dispatchers.IO
    override val mainImmediate = Dispatchers.Main.immediate
    override val background = Dispatchers.Background
    override val default = Dispatchers.Default
}

class TestDispatcherProvider : DispatcherProvider {
    override val io = Dispatchers.Unconfined
    override val mainImmediate = Dispatchers.Unconfined
    override val background = Dispatchers.Unconfined
    override val default = Dispatchers.Unconfined
}