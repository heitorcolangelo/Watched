package com.heitorcolangelo.data.remote.dummy.di

import com.heitorcolangelo.data.remote.common.api.ApiServiceFactory
import com.heitorcolangelo.data.remote.dummy.api.DummyApiService
import com.heitorcolangelo.data.remote.dummy.mapper.DummyResponseDataMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DummyRemoteDataModule {

    @Singleton
    @Provides
    fun provideDummyResponseDataMapper() = DummyResponseDataMapper

    @Singleton
    @Provides
    fun provideDummyApiService(apiServiceFactory: ApiServiceFactory): DummyApiService {
        return apiServiceFactory.initService(DummyApiService::class.java, DummyApiService.BASE_URL)
    }
}
