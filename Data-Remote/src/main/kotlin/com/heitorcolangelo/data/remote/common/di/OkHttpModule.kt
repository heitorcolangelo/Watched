package com.heitorcolangelo.data.remote.common.di

import com.heitorcolangelo.data.remote.common.api.OkHttpClientFactory
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Singleton
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
class OkHttpModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        return OkHttpClientFactory.getLoggingInterceptor(isDebug)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptors: List<Interceptor>, cache: Cache?): OkHttpClient {
        return OkHttpClientFactory.getClient(interceptors, cache)
    }

    @Singleton
    @Provides
    fun provideCache(): Cache {
        val cacheSize = 10L * 1024L * 1024L // 10 MB
        val cacheDir = File("cache/dir")
        return Cache(cacheDir, cacheSize)
    }
}
