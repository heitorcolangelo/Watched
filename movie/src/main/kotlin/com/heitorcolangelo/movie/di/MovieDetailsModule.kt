package com.heitorcolangelo.movie.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.heitorcolangelo.data.local.movie.di.MovieLocalDataModule
import com.heitorcolangelo.data.movie.di.MovieDataModule
import com.heitorcolangelo.data.remote.movie.di.MovieRemoteDataModule
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.domain.GetMovieUseCase
import com.heitorcolangelo.movie.mapper.MovieDetailsDomainUiMapper
import com.heitorcolangelo.movie.model.MovieDetailsUiModel
import com.heitorcolangelo.movie.ui.detail.MovieDetailsFragment
import com.heitorcolangelo.movie.ui.detail.MovieDetailsViewModel
import com.heitorcolangelo.presentation.common.model.DomainUiMapper
import com.heitorcolangelo.presentation.di.ApplicationModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.util.Locale
import org.threeten.bp.ZoneId

@Module(
    includes = [
        MovieDataModule::class,
        MovieLocalDataModule::class,
        MovieRemoteDataModule::class
    ]
)
abstract class MovieDetailsModule : ApplicationModule() {
    @Binds
    abstract fun bindMovieDetailsFragment(fragment: MovieDetailsFragment): Fragment

    @Binds
    abstract fun bindMovieDetailsDomainUiMapper(mapper: MovieDetailsDomainUiMapper): DomainUiMapper<MovieDomainModel, MovieDetailsUiModel>

    @Module
    companion object {
        @Provides
        fun provideMovieDetailsViewModelFactory(
            mapper: MovieDetailsDomainUiMapper,
            useCase: GetMovieUseCase
        ): MovieDetailsViewModel.Factory {
            return MovieDetailsViewModel.Factory(mapper, useCase)
        }

        @Provides
        fun provideMovieDetailsViewModel(
            fragment: MovieDetailsFragment,
            factory: MovieDetailsViewModel.Factory
        ): MovieDetailsViewModel {
            return ViewModelProvider(fragment, factory).get(MovieDetailsViewModel::class.java)
        }

        @Provides
        fun provideZoneId(): ZoneId {
            return ZoneId.systemDefault()
        }

        @Provides
        fun provideLocale(): Locale {
            return Locale.getDefault()
        }
    }
}
