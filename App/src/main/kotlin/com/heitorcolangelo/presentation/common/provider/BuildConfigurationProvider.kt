package com.heitorcolangelo.presentation.common.provider

import com.heitorcolangelo.BuildConfig
import com.heitorcolangelo.data.remote.common.BuildConfiguration
import javax.inject.Inject

class BuildConfigurationProvider @Inject constructor() : BuildConfiguration {
    override fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun apiKey(): String {
        return BuildConfig.API_KEY
    }

    override fun imageBaseUrl(): String {
        return BuildConfig.BASE_IMAGE_URL
    }
}
