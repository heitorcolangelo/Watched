package com.heitorcolangelo.presentation.common.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.heitorcolangelo.data.local.di.LocalDataModule
import com.heitorcolangelo.data.remote.di.RemoteDataModule
import com.heitorcolangelo.presentation.applicationComponent
import com.heitorcolangelo.presentation.common.provider.BuildConfigurationProvider

abstract class BaseActivity : AppCompatActivity() {
    @get:LayoutRes
    protected abstract val layoutToInflate: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutToInflate)

        DaggerBaseActivityComponent.builder()
            .applicationComponent(this.applicationComponent())
            .localDataModule(LocalDataModule(application))
            .remoteDataModule(RemoteDataModule(BuildConfigurationProvider()))
            .activity(this)
            .build()
            .inject(this)
    }
}
