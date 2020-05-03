package com.heitorcolangelo.presentation.common.provider

import com.heitorcolangelo.BuildConfig
import com.heitorcolangelo.data.remote.common.BuildConfiguration

class BuildConfigurationProvider : BuildConfiguration {
    override fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    override fun apiKey(): String {
        return BuildConfig.API_KEY
    }
}
