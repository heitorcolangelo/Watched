package com.heitorcolangelo.data.local.common.di

import android.content.Context
import com.heitorcolangelo.data.local.common.db.SkeletonDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): SkeletonDatabase {
        return SkeletonDatabase.getDatabase(context)
    }
}
