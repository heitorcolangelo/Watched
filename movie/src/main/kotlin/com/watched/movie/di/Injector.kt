package com.watched.movie.di

import com.watched.data.local.di.LocalDataModule
import com.watched.data.remote.di.RemoteDataModule
import com.watched.movie.ui.detail.MovieDetailsFragment
import com.watched.movie.ui.main.MovieMainFragment
import com.watched.presentation.common.provider.BuildConfigurationProvider

internal fun MovieMainFragment.inject() {
    DaggerMovieMainComponent.builder()
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
