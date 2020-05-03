package com.heitorcolangelo.data.local.di

import android.app.Application
import com.heitorcolangelo.data.local.common.db.SkeletonDatabase
import com.heitorcolangelo.data.local.config.dao.ConfigDao
import dagger.Module
import dagger.Provides

@Module
class LocalDataModule(private val application: Application) {
    @Provides
    fun provideSkeletonDatabase(): SkeletonDatabase {
        return SkeletonDatabase.getDatabase(application)
    }

    @Provides
    fun provideConfigDao(database: SkeletonDatabase): ConfigDao {
        return database.getConfigDao()
    }
}
