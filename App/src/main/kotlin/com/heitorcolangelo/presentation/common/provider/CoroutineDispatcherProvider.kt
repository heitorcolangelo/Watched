package com.heitorcolangelo.presentation.common.provider

import com.heitorcolangelo.domain.common.providers.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CoroutineDispatcherProvider @Inject constructor() : DispatcherProvider {
    override fun io() = Dispatchers.IO

    override fun ui() = Dispatchers.Main
}
