package com.heitorcolangelo.data.local.common.di

import dagger.Component

@Component(modules = [LocalDataModule::class, ConfigModule::class])
interface LocalDataComponent
