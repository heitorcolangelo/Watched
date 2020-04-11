package com.heitorcolangelo.data.remote.di

import dagger.Subcomponent

@Subcomponent(modules = [OkHttpModule::class, RetrofitModule::class])
interface RemoteDataComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): RemoteDataComponent
    }
}
