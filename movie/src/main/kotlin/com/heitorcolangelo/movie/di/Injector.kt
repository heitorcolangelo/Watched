package com.heitorcolangelo.movie.di

import com.heitorcolangelo.data.local.di.LocalDataModule
import com.heitorcolangelo.data.remote.di.RemoteDataModule
import com.heitorcolangelo.movie.ui.detail.MovieDetailsFragment
import com.heitorcolangelo.movie.ui.list.MovieListFragment
import com.heitorcolangelo.presentation.common.provider.BuildConfigurationProvider

internal fun MovieListFragment.inject() {
    DaggerMovieFeatureComponent.builder()
        .localDataModule(LocalDataModule(this.requireActivity().application))
        .remoteDataModule(RemoteDataModule(BuildConfigurationProvider()))
        .fragment(this)
        .build()
        .inject(this)
}

internal fun MovieDetailsFragment.inject() {
    DaggerMovieDetailsComponent.builder()
        .localDataModule(LocalDataModule(this.requireActivity().application))
        .remoteDataModule(RemoteDataModule(BuildConfigurationProvider()))
        .fragment(this)
        .build()
        .inject(this)
}
