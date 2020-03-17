package com.heitorcolangelo.data.local.dummy.di

import com.heitorcolangelo.data.dummy.source.DummyLocalData
import com.heitorcolangelo.data.local.dummy.DummyLocalDataImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DummyLocalDataBinder {
    @Singleton
    @Binds
    abstract fun provideLocalData(localDataImpl: DummyLocalDataImpl): DummyLocalData
}