package com.heitorcolangelo.data.local.dummy.di

import com.heitorcolangelo.data.dummy.source.DummyLocalData
import com.heitorcolangelo.data.local.dummy.DummyLocalDataImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DummyLocalDataModule {

    @Binds
    abstract fun bindDummyLocalData(impl: DummyLocalDataImpl): DummyLocalData
}
