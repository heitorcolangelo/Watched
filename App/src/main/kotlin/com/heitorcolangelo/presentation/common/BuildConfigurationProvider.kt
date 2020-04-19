package com.heitorcolangelo.presentation.common

import com.heitorcolangelo.data.remote.common.BuildConfiguration
import com.heitorcolangelo.skeleton.BuildConfig

class BuildConfigurationProvider : BuildConfiguration {
    override fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }
}