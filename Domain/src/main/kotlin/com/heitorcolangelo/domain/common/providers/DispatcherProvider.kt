package com.heitorcolangelo.domain.common.providers

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun io(): CoroutineDispatcher
    fun ui(): CoroutineDispatcher
}
