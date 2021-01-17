package com.watched.presentation.common.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.watched.data.local.di.LocalDataModule
import com.watched.data.remote.di.RemoteDataModule
import com.watched.presentation.applicationComponent
import com.watched.presentation.common.provider.BuildConfigurationProvider

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
