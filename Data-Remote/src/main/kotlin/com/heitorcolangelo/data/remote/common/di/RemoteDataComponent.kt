package com.heitorcolangelo.data.remote.common.di

import com.heitorcolangelo.data.remote.common.api.ApiServiceFactory
import dagger.Component

@Component(modules = [OkHttpModule::class, RetrofitModule::class])
interface RemoteDataComponent {
    fun inject(factory: ApiServiceFactory)
}
