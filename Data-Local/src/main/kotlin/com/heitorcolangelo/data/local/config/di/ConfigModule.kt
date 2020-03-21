package com.heitorcolangelo.data.local.config.di

import com.heitorcolangelo.data.local.common.db.SkeletonDatabase
import com.heitorcolangelo.data.local.config.dao.ConfigDao
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
