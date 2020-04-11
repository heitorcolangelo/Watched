package com.heitorcolangelo.data.remote.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
abstract class RetrofitModule {

    @Module
    companion object {
        @Provides
        fun provideConverterFactory(): Converter.Factory {
            val moshi = Moshi.Builder().build()
            return MoshiConverterFactory.create(moshi)
        }

        @Provides
        fun provideCallAdapterFactory(): CallAdapter.Factory {
            return RxJava2CallAdapterFactory.create()
        }
    }

}
