package com.watched.data.remote.common.api

import com.watched.data.remote.common.BuildConfiguration
import okhttp3.logging.HttpLoggingInterceptor

internal object LoggingInterceptorFactory {
    internal fun getLoggingInterceptor(configuration: BuildConfiguration): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (configuration.isDebug()) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }
}
