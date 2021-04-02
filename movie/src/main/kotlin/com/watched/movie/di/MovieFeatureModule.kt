package com.watched.movie.di

import androidx.fragment.app.Fragment
import com.watched.data.local.movie.di.MovieLocalDataModule
import com.watched.data.remote.movie.di.MovieRemoteDataModule
import com.watched.domain.common.providers.DispatcherProvider
import com.watched.domain.media.MediaDomainModel
import com.watched.movie.data.di.MovieDataModule
import com.watched.movie.domain.usecase.GetMovieUseCase
import com.watched.movie.domain.usecase.GetSortedMoviesUseCase
import com.watched.movie.domain.usecase.GetTopXMovieUseCase
import com.watched.movie.ui.detail.MovieDetailsFragment
import com.watched.movie.ui.detail.MovieDetailsViewModel
import com.watched.movie.ui.main.MovieMainFragment
import com.watched.movie.ui.main.MovieMainViewModel
import com.watched.movie.ui.mapper.MovieDetailsDomainUiMapper
import com.watched.movie.ui.mapper.MovieTopXDomainUiMapper
import com.watched.presentation.common.mapper.ListDomainUiMapper
import com.watched.presentation.common.mapper.ListDomainUiMapperImpl
import com.watched.presentation.common.mapper.PageDomainUiMapper
import com.watched.presentation.common.mapper.PageDomainUiMapperImpl
import com.watched.presentation.common.viewmodel.ViewModelFactory
import com.watched.presentation.di.ApplicationModule
import com.watched.presentation.media.mapper.MediaItemDomainUiMapper
import com.watched.presentation.media.mapper.MediaSectionDomainUiMapper
import com.watched.presentation.media.model.MediaItemUiModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.threeten.bp.ZoneId
import java.util.Locale

@Module(
    includes = [
        MovieDataModule::class,
        MovieLocalDataModule::class,
        MovieRemoteDataModule::class
    ]
)
abstract class MovieFeatureModule : ApplicationModule() {

    @Binds
    abstract fun bindMovieDetailsFragment(fragment: MovieDetailsFragment): Fragment

    companion object {
        @Provides
        fun provideMovieListDomainUiMapper(
            mapper: MediaItemDomainUiMapper
        ): ListDomainUiMapper<MediaDomainModel, MediaItemUiModel> {
            return ListDomainUiMapperImpl(mapper)
        }

        @Provides
        fun provideMoviePageDomainUiMapper(
            mapper: MediaItemDomainUiMapper
        ): PageDomainUiMapper<MediaDomainModel, MediaItemUiModel> {
            return PageDomainUiMapperImpl(mapper)
        }

        @Provides
        fun provideMovieDetailsViewModel(
            fragment: MovieDetailsFragment,
            mapper: MovieDetailsDomainUiMapper,
            useCase: GetMovieUseCase,
            dispatcherProvider: DispatcherProvider
        ): MovieDetailsViewModel {
            return ViewModelFactory.make(fragment) {
                MovieDetailsViewModel(
                    mapper,
                    useCase,
                    dispatcherProvider
                )
            }
        }

        @Provides
        fun provideMovieMainViewModel(
            fragment: MovieMainFragment,
            mapper: MovieTopXDomainUiMapper,
            useCase: GetTopXMovieUseCase,
            sortedUseCase: GetSortedMoviesUseCase,
            movieSectionMapper: MediaSectionDomainUiMapper,
            dispatcherProvider: DispatcherProvider
        ): MovieMainViewModel {
            return ViewModelFactory.make(fragment) {
                MovieMainViewModel(
                    useCase,
                    mapper,
                    sortedUseCase,
                    movieSectionMapper,
                    dispatcherProvider
                )
            }
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
