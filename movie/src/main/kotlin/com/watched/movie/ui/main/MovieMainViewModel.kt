package com.watched.movie.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watched.domain.common.providers.DispatcherProvider
import com.watched.domain.movie.model.PopularMoviesDomainModel
import com.watched.movie.domain.GetPopularMoviesUseCase
import com.watched.movie.domain.GetTopXMovieUseCase
import com.watched.movie.mapper.MovieSectionDomainUiMapper
import com.watched.movie.mapper.TopXMovieDomainUiMapper
import com.watched.movie.model.MovieSectionItemUiModel
import com.watched.movie.model.TopXMovieUiModel
import com.watched.presentation.common.handler.ExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MovieMainViewModel @Inject constructor(
    private val topXMovieUseCase: GetTopXMovieUseCase,
    private val topXMovieMapper: TopXMovieDomainUiMapper,
    private val popularMoviesUseCase: GetPopularMoviesUseCase,
    private val movieSectionMapper: MovieSectionDomainUiMapper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _topXMovie = MutableLiveData<TopXMovieUiModel>()
    val topXMovie: LiveData<TopXMovieUiModel> = _topXMovie

    private val _popularMovies = MutableLiveData<MovieSectionItemUiModel>()
    val popularMovies: LiveData<MovieSectionItemUiModel> = _popularMovies

    init {
        getTopXMovie()
        getPopularMovies()
    }

    private fun getTopXMovie() {
        viewModelScope.launch(
            dispatcherProvider.io() + TopXMovieExceptionHandler()
        ) {
            val topXMovie = topXMovieUseCase.execute()
            val uiModel = topXMovieMapper.mapToUiModel(topXMovie)
            _topXMovie.postValue(uiModel)
        }
    }

    private fun getPopularMovies(forceRefresh: Boolean = false) {
        viewModelScope.launch(
            dispatcherProvider.io() + PopularMoviesExceptionHandler()
        ) {
            val args = GetPopularMoviesUseCase.Args(forceRefresh)
            val popularMovies: PopularMoviesDomainModel = popularMoviesUseCase.execute(args)
            val popularMoviesSection: MovieSectionItemUiModel = movieSectionMapper.mapToUiModel(popularMovies)
            _popularMovies.postValue(popularMoviesSection)
        }
    }

    class TopXMovieExceptionHandler : ExceptionHandler {
        override fun handleException(context: CoroutineContext, exception: Throwable) {
            println("GetTopXMovieUseCase throws $exception.")
        }
    }

    class PopularMoviesExceptionHandler : ExceptionHandler {
        override fun handleException(context: CoroutineContext, exception: Throwable) {
            println("GetPopularMoviesUseCase throws $exception.")
        }
    }
}
