package com.heitorcolangelo.data.local.di

import android.content.Context
import com.heitorcolangelo.data.local.common.db.SkeletonDatabase
import com.heitorcolangelo.data.local.dummy.di.DummyLocalDataComponent
import dagger.Module
import dagger.Provides

@Module(subcomponents = [DummyLocalDataComponent::class])
abstract class LocalDataModule {
    @Module
    companion object {
        @Provides
        fun provideDatabase(context: Context): SkeletonDatabase {
            return SkeletonDatabase.getDatabase(context)
        }
    }
}
