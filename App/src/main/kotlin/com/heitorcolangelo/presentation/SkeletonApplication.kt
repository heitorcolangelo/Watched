package com.heitorcolangelo.presentation

import android.app.Application
import com.heitorcolangelo.presentation.di.DaggerApplicationComponent

class SkeletonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}