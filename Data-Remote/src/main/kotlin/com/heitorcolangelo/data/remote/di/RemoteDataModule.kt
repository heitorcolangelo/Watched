package com.heitorcolangelo.data.remote.di

import com.heitorcolangelo.data.remote.common.BuildConfiguration
import com.heitorcolangelo.data.remote.common.api.AuthInterceptor
import com.heitorcolangelo.data.remote.common.api.OkHttpClientFactory
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.Date

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
    fun provideConverterFactory(dateAdapter: JsonAdapter<Date>): Converter.Factory {
        val moshi = Moshi.Builder()
            .add(Date::class.java, dateAdapter)
            .build()
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    fun provideDateAdapter(): JsonAdapter<Date> {
        return Rfc3339DateJsonAdapter().nullSafe()
    }

    @Provides
    fun provideCache(): Cache {
        val cacheSize = 10L * 1024L * 1024L // 10 MB
        val cacheDir = File("cache/dir")
        return Cache(cacheDir, cacheSize)
    }

    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory {
        return RxJava3CallAdapterFactory.create()
    }

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return OkHttpClientFactory.getLoggingInterceptor(configuration)
    }

    @Provides
    fun provideAuthInterceptor(): AuthInterceptor {
        return AuthInterceptor(configuration.apiKey())
    }
}
