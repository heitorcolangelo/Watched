package com.heitorcolangelo.data.remote.di

import com.heitorcolangelo.data.remote.common.api.OkHttpClientFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File

@Module
internal class RemoteDataModule {
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClientFactory.getClient(listOf(loggingInterceptor), cache)
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
    fun provideCallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }
}