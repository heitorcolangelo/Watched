package com.heitorcolangelo.data.di

import com.heitorcolangelo.data.dummy.di.DummyDataComponent
import dagger.Module

@Module(subcomponents = [DummyDataComponent::class])
abstract class DataModule