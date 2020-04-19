package com.heitorcolangelo.data.local.dummy.di

import com.heitorcolangelo.data.dummy.model.DummyDataModel
import com.heitorcolangelo.data.dummy.source.DummyLocalData
import com.heitorcolangelo.data.local.common.db.SkeletonDatabase
import com.heitorcolangelo.data.local.common.mapper.EntityDataMapper
import com.heitorcolangelo.data.local.dummy.DummyLocalDataImpl
import com.heitorcolangelo.data.local.dummy.dao.DummyDao
import com.heitorcolangelo.data.local.dummy.entity.DummyEntity
import com.heitorcolangelo.data.local.dummy.mapper.DummyEntityDataMapper
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DummyLocalDataModule {

    @Binds
    abstract fun bindDummyLocalData(impl: DummyLocalDataImpl): DummyLocalData

    @Binds
    abstract fun bindDummyEntityDataMapper(mapper: DummyEntityDataMapper): EntityDataMapper<DummyEntity, DummyDataModel>

    @Module
    companion object {
        @Provides
        fun provideDummyDao(database: SkeletonDatabase): DummyDao {
            return database.getDummyDao()
        }
    }
}
