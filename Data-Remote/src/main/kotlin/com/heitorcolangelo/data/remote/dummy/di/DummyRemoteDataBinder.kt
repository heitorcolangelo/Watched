package com.heitorcolangelo.data.remote.dummy.di

import com.heitorcolangelo.data.dummy.source.DummyRemoteData
import com.heitorcolangelo.data.remote.dummy.DummyRemoteDataImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DummyRemoteDataBinder {

    @Singleton
    @Binds
    abstract fun provideDummyRemoteData(dummyRemoteDataImpl: DummyRemoteDataImpl): DummyRemoteData
}