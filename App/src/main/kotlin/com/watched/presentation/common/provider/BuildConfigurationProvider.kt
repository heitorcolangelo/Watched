package com.watched.presentation.common.provider

import com.watched.BuildConfig
import com.watched.data.remote.common.BuildConfiguration
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
