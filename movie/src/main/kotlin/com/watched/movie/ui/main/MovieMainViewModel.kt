package com.watched.movie.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watched.domain.common.providers.DispatcherProvider
import com.watched.domain.media.SortedMediaDomainModel
import com.watched.domain.common.model.SortOptionsDomainModel
import com.watched.movie.domain.usecase.GetSortedMoviesUseCase
import com.watched.movie.domain.usecase.GetTopXMovieUseCase
import com.watched.presentation.media.mapper.MediaSectionDomainUiMapper
import com.watched.movie.ui.mapper.MovieTopXDomainUiMapper
import com.watched.presentation.common.handler.ExceptionHandler
import com.watched.presentation.media.model.MediaSectionItemUiModel
import com.watched.presentation.media.model.MediaTopXUiModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MovieMainViewModel @Inject constructor(
    private val topXMovieUseCase: GetTopXMovieUseCase,
    private val topXMovieMapper: MovieTopXDomainUiMapper,
    private val sortedMoviesUseCase: GetSortedMoviesUseCase,
    private val movieSectionMapper: MediaSectionDomainUiMapper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _topXMovie = MutableLiveData<MediaTopXUiModel>()
    val topXMovie: LiveData<MediaTopXUiModel> = _topXMovie

    private val _popularMovies = MutableLiveData<MediaSectionItemUiModel>()
    private val _topRatedMovies = MutableLiveData<MediaSectionItemUiModel>()

    val sectionMovies = MediatorLiveData<List<MediaSectionItemUiModel>>()

    init {
        sectionMovies.addSource(_popularMovies) {
            _topRatedMovies.value?.let { topRatedItems ->
                sectionMovies.postValue(listOf(it, topRatedItems))
            }
        }
        sectionMovies.addSource(_topRatedMovies) {
            _popularMovies.value?.let { popularItems ->
                sectionMovies.postValue(listOf(popularItems, it))
            }
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
            val popularMovies: SortedMediaDomainModel = sortedMoviesUseCase.execute(args)
            val popularMoviesSection: MediaSectionItemUiModel =
                movieSectionMapper.mapToUiModel(popularMovies)
            _popularMovies.postValue(popularMoviesSection)
        }
    }

    private fun getTopRatedMovies(forceRefresh: Boolean = false) {
        viewModelScope.launch(
            dispatcherProvider.io() + TopRatedMoviesExceptionHandler()
        ) {
            val args = GetSortedMoviesUseCase.Args(forceRefresh, SortOptionsDomainModel.TopRated)
            val topRatedMovies: SortedMediaDomainModel = sortedMoviesUseCase.execute(args)
            val topRatedSection: MediaSectionItemUiModel =
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
