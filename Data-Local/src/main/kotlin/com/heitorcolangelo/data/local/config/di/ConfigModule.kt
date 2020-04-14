package com.heitorcolangelo.data.local.config.di

import com.heitorcolangelo.data.local.common.db.SkeletonDatabase
import com.heitorcolangelo.data.local.config.dao.ConfigDao
import dagger.Module
import dagger.Provides

@Module
class ConfigModule {
    @Provides
    fun provideConfigDao(database: SkeletonDatabase): ConfigDao {
        return database.getConfigDao()
    }
}
