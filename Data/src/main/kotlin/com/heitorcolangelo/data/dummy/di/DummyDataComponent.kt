package com.heitorcolangelo.data.dummy.di

import dagger.Subcomponent

@Subcomponent(modules = [DummyDataModule::class])
interface DummyDataComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): DummyDataComponent
    }
}