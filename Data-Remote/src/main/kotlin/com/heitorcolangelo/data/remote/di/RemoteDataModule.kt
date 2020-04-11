package com.heitorcolangelo.data.remote.di

import com.heitorcolangelo.data.remote.dummy.di.DummyRemoteDataComponent
import dagger.Module

@Module(
    subcomponents = [
        DummyRemoteDataComponent::class
    ]
)
abstract class RemoteDataModule