package com.heitorcolangelo.data.local.dummy.di

import com.heitorcolangelo.data.local.common.db.SkeletonDatabase
import com.heitorcolangelo.data.local.dummy.dao.DummyDao
import com.heitorcolangelo.data.local.dummy.mapper.DummyEntityDataMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DummyLocalDataModule {

    @Singleton
    @Provides
    fun provideDummyEntityDataMapper() = DummyEntityDataMapper

    @Singleton
    @Provides
    fun provideDummyDao(database: SkeletonDatabase): DummyDao {
        return database.getDummyDao()
    }
}
