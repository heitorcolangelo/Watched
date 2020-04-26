package com.heitorcolangelo.data.remote.common.api

import com.heitorcolangelo.data.remote.common.BuildConfiguration
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

internal object OkHttpClientFactory {
    internal fun getClient(interceptors: List<Interceptor>, cache: Cache?): OkHttpClient {
        var builder = OkHttpClient.Builder()
        interceptors.forEach {
            builder = builder.addInterceptor(it)
        }
        builder.cache(cache)
        return builder.build()
    }

    internal fun getLoggingInterceptor(configuration: BuildConfiguration): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (configuration.isDebug()) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }
}
