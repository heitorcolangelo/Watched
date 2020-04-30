package com.heitorcolangelo.presentation.common

import com.heitorcolangelo.BuildConfig
import com.heitorcolangelo.data.remote.common.BuildConfiguration

class BuildConfigurationProvider : BuildConfiguration {
    override fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }
}
