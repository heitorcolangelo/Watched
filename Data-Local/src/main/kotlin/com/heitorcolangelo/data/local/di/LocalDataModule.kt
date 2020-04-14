package com.heitorcolangelo.data.local.di

import android.content.Context
import com.heitorcolangelo.data.local.common.db.SkeletonDatabase
import dagger.Module
import dagger.Provides

@Module
class LocalDataModule {
    @Provides
    fun provideSkeletonDatabase(context: Context): SkeletonDatabase {
        return SkeletonDatabase.getDatabase(context)
    }
}
