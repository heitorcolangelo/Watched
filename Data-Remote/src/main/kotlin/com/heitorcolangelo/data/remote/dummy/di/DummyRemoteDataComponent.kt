package com.heitorcolangelo.data.remote.dummy.di

import com.heitorcolangelo.data.remote.dummy.DummyRemoteDataImpl
import com.heitorcolangelo.data.remote.dummy.mapper.DummiesResponseModelMapper
import dagger.Component

@Component(modules = [DummyRemoteDataModule::class])
interface DummyRemoteDataComponent {
    fun inject(remoteData: DummyRemoteDataImpl)
    fun inject(dummiesMapper: DummiesResponseModelMapper)
}
