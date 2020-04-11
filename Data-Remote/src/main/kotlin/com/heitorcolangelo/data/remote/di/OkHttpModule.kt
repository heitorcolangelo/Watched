package com.heitorcolangelo.data.remote.di

import com.heitorcolangelo.data.remote.common.api.OkHttpClientFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

@Module
abstract class OkHttpModule {

    @Module
    companion object {
        @Provides
        fun provideLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
            return OkHttpClientFactory.getLoggingInterceptor(isDebug)
        }

        @Provides
        fun provideOkHttpClient(interceptors: List<Interceptor>, cache: Cache?): OkHttpClient {
            return OkHttpClientFactory.getClient(interceptors, cache)
        }

        @Provides
        fun provideCache(): Cache {
            val cacheSize = 10L * 1024L * 1024L // 10 MB
            val cacheDir = File("cache/dir")
            return Cache(cacheDir, cacheSize)
        }
    }
}
