package com.heitorcolangelo.data.local.dummy.di

import dagger.Subcomponent

@Subcomponent(modules = [DummyLocalDataModule::class])
interface DummyLocalDataComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): DummyLocalDataComponent
    }
}