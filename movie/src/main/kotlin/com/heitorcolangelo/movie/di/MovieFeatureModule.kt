package com.heitorcolangelo.movie.di

import androidx.fragment.app.Fragment
import com.heitorcolangelo.data.local.movie.di.MovieLocalDataModule
import com.heitorcolangelo.data.movie.di.MovieDataModule
import com.heitorcolangelo.data.remote.movie.di.MovieRemoteDataModule
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.movie.domain.GetMovieUseCase
import com.heitorcolangelo.movie.domain.GetPopularMoviesUseCase
import com.heitorcolangelo.movie.mapper.MovieDetailsDomainUiMapper
import com.heitorcolangelo.movie.mapper.MovieItemDomainUiMapper
import com.heitorcolangelo.movie.model.MovieDetailsUiModel
import com.heitorcolangelo.movie.model.MovieItemUiModel
import com.heitorcolangelo.movie.ui.detail.MovieDetailsFragment
import com.heitorcolangelo.movie.ui.detail.MovieDetailsViewModel
import com.heitorcolangelo.movie.ui.list.MovieListFragment
import com.heitorcolangelo.movie.ui.list.MovieListViewModel
import com.heitorcolangelo.presentation.common.model.DomainUiMapper
import com.heitorcolangelo.presentation.common.viewmodel.ViewModelFactory
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
abstract class MovieFeatureModule : ApplicationModule() {
    @Binds
    abstract fun bindMovieListFragment(fragment: MovieListFragment): Fragment

    @Binds
    abstract fun bindMovieItemDomainUiMapper(mapper: MovieItemDomainUiMapper): DomainUiMapper<MovieDomainModel, MovieItemUiModel>

    @Binds
    abstract fun bindMovieDetailsFragment(fragment: MovieDetailsFragment): Fragment

    @Binds
    abstract fun bindMovieDetailsDomainUiMapper(mapper: MovieDetailsDomainUiMapper): DomainUiMapper<MovieDomainModel, MovieDetailsUiModel>

    @Module
    companion object {
        @Provides
        fun provideMovieListViewModel(
            fragment: MovieListFragment,
            mapper: MovieItemDomainUiMapper,
            useCase: GetPopularMoviesUseCase
        ): MovieListViewModel {
            return ViewModelFactory.make(fragment) { MovieListViewModel(mapper, useCase) }
        }

        @Provides
        fun provideMovieDetailsViewModel(
            fragment: MovieDetailsFragment,
            mapper: MovieDetailsDomainUiMapper,
            useCase: GetMovieUseCase
        ): MovieDetailsViewModel {
            return ViewModelFactory.make(fragment) { MovieDetailsViewModel(mapper, useCase) }
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
