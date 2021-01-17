package com.watched.presentation.common.provider

import com.watched.domain.common.providers.DispatcherProvider
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

class CoroutineDispatcherProvider @Inject constructor() : DispatcherProvider {
    override fun io() = Dispatchers.IO

    override fun ui() = Dispatchers.Main
}
