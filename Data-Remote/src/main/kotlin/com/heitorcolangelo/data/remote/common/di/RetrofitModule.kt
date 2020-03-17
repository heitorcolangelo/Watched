package com.heitorcolangelo.data.remote.common.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory {
        val moshi = Moshi.Builder().build()
        return MoshiConverterFactory.create(moshi)
    }

    @Singleton
    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }
}
