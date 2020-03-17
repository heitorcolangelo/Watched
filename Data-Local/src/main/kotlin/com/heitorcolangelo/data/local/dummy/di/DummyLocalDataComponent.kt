package com.heitorcolangelo.data.local.dummy.di

import com.heitorcolangelo.data.local.dummy.DummyLocalDataImpl
import dagger.Component

@Component(modules = [DummyLocalDataModule::class, DummyLocalDataBinder::class])
interface DummyLocalDataComponent {
    fun inject(localDataImpl: DummyLocalDataImpl)
}
