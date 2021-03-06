package com.watched.presentation.di.common

import android.app.Activity
import androidx.fragment.app.Fragment
import com.watched.data.local.di.LocalDataModule
import com.watched.data.remote.di.RemoteDataModule

interface FeatureComponent<T> {
    fun inject(target: T)

    interface Builder<ComponentBuilder> {
        fun localDataModule(module: LocalDataModule): ComponentBuilder
        fun remoteDataModule(module: RemoteDataModule): ComponentBuilder
    }
}

interface FragmentComponent<F : Fragment> : FeatureComponent<F>

interface ActivityComponent<A : Activity> : FeatureComponent<A>
