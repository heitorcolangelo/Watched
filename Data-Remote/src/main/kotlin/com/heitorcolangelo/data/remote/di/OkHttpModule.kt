package com.heitorcolangelo.data.remote.di

import com.heitorcolangelo.data.remote.common.api.OkHttpClientFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

@Module
abstract class OkHttpModule {

    @Module
    companion object {
        @Provides
        fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
            return OkHttpClientFactory.getClient(listOf(loggingInterceptor), cache)
        }

        @Provides
        fun provideCache(): Cache {
            val cacheSize = 10L * 1024L * 1024L // 10 MB
            val cacheDir = File("cache/dir")
            return Cache(cacheDir, cacheSize)
        }
    }
}
