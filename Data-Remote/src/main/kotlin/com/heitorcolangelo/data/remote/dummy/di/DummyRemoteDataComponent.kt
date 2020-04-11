package com.heitorcolangelo.data.remote.dummy.di

import dagger.Subcomponent

@Subcomponent(modules = [DummyRemoteDataModule::class])
interface DummyRemoteDataComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): DummyRemoteDataComponent
    }
}
