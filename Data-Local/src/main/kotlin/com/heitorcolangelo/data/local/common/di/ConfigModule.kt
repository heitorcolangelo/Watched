package com.heitorcolangelo.data.local.common.di

import com.heitorcolangelo.data.local.common.dao.ConfigDao
import com.heitorcolangelo.data.local.common.db.SkeletonDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ConfigModule {
    @Singleton
    @Provides
    fun provideConfigDao(database: SkeletonDatabase): ConfigDao {
        return database.getConfigDao()
    }
}
