package com.heitorcolangelo.data.remote.di

import com.heitorcolangelo.data.remote.common.BuildConfiguration
import com.heitorcolangelo.data.remote.common.api.AuthInterceptor
import com.heitorcolangelo.data.remote.common.api.LoggingInterceptorFactory
import com.heitorcolangelo.data.remote.common.api.OkHttpClientFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import java.io.File
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class RemoteDataModule(private val configuration: BuildConfiguration) {
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClientFactory.getClient(listOf(loggingInterceptor, authInterceptor), cache)
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        val moshi = Moshi.Builder().build()
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideCache(): Cache {
        val cacheSize = 10L * 1024L * 1024L // 10 MB
        val cacheDir = File("cache/dir")
        return Cache(cacheDir, cacheSize)
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return LoggingInterceptorFactory.getLoggingInterceptor(configuration)
    }

    @Provides
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor(configuration.apiKey())
    }
}
