package com.heitorcolangelo.data.remote.dummy.di

import com.heitorcolangelo.data.dummy.source.DummyRemoteData
import com.heitorcolangelo.data.remote.common.api.ApiServiceFactory
import com.heitorcolangelo.data.remote.dummy.DummyRemoteDataImpl
import com.heitorcolangelo.data.remote.dummy.api.DummyApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DummyRemoteDataModule {

    @Module
    companion object {
        @Singleton
        @Provides
        fun provideDummyApiService(apiServiceFactory: ApiServiceFactory): DummyApiService {
            return apiServiceFactory.initService(
                DummyApiService::class.java,
                DummyApiService.BASE_URL
            )
        }
    }

    @Binds
    abstract fun bindDummyRemoteData(impl: DummyRemoteDataImpl): DummyRemoteData
}
