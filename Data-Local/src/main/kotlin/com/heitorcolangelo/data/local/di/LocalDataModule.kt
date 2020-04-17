package com.heitorcolangelo.data.local.di

import android.content.Context
import com.heitorcolangelo.data.local.common.db.SkeletonDatabase
import com.heitorcolangelo.data.local.config.dao.ConfigDao
import dagger.Module
import dagger.Provides

@Module
internal class LocalDataModule {
    @Provides
    fun provideSkeletonDatabase(context: Context): SkeletonDatabase {
        return SkeletonDatabase.getDatabase(context)
    }

    @Provides
    fun provideConfigDao(database: SkeletonDatabase): ConfigDao {
        return database.getConfigDao()
    }
}
