package com.heitorcolangelo.data.remote.common

/**
 * Interface that will provide to Data-Remote layer the information regarding which build configuration
 * is being used. For example:
 * - BuildConfig.DEBUG
 */
interface BuildConfiguration {
    fun isDebug(): Boolean
}