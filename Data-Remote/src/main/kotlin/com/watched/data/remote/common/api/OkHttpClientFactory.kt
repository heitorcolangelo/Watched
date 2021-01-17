package com.watched.data.remote.common.api

import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient

internal object OkHttpClientFactory {
    internal fun getClient(interceptors: List<Interceptor>, cache: Cache?): OkHttpClient {
        var builder = OkHttpClient.Builder()
        interceptors.forEach {
            builder = builder.addInterceptor(it)
        }
        builder.cache(cache)
        return builder.build()
    }
}
