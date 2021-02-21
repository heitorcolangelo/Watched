package com.watched.movie.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watched.domain.common.providers.DispatcherProvider
import com.watched.domain.movie.model.MovieListDomainModel
import com.watched.domain.movie.model.SortOptionsDomainModel
import com.watched.movie.domain.GetSortedMoviesUseCase
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
    private val sortedMoviesUseCase: GetSortedMoviesUseCase,
    private val movieSectionMapper: MovieSectionDomainUiMapper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _topXMovie = MutableLiveData<TopXMovieUiModel>()
    val topXMovie: LiveData<TopXMovieUiModel> = _topXMovie

    private val _popularMovies = MutableLiveData<MovieSectionItemUiModel>()
    private val _topRatedMovies = MutableLiveData<MovieSectionItemUiModel>()

    val sectionMovies = MediatorLiveData<List<MovieSectionItemUiModel?>>()

    init {
        sectionMovies.addSource(_popularMovies) {
            sectionMovies.postValue(listOf(it, _topRatedMovies.value))
        }
        sectionMovies.addSource(_topRatedMovies) {
            sectionMovies.postValue(listOf(_popularMovies.value, it))
        }

        getTopXMovie()
        getPopularMovies()
        getTopRatedMovies()
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
            val args = GetSortedMoviesUseCase.Args(forceRefresh, SortOptionsDomainModel.Popularity)
            val popularMovies: MovieListDomainModel = sortedMoviesUseCase.execute(args)
            val popularMoviesSection: MovieSectionItemUiModel =
                movieSectionMapper.mapToUiModel(popularMovies)
            _popularMovies.postValue(popularMoviesSection)
        }
    }

    private fun getTopRatedMovies(forceRefresh: Boolean = false) {
        viewModelScope.launch(
            dispatcherProvider.io() + TopRatedMoviesExceptionHandler()
        ) {
            val args = GetSortedMoviesUseCase.Args(forceRefresh, SortOptionsDomainModel.TopRated)
            val topRatedMovies: MovieListDomainModel = sortedMoviesUseCase.execute(args)
            val topRatedSection: MovieSectionItemUiModel =
                movieSectionMapper.mapToUiModel(topRatedMovies)
            _topRatedMovies.postValue(topRatedSection)
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

    class TopRatedMoviesExceptionHandler : ExceptionHandler {
        override fun handleException(context: CoroutineContext, exception: Throwable) {
            println("GetPopularMoviesUseCase throws $exception.")
        }
    }
}
