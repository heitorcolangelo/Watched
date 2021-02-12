package com.watched.movie.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watched.domain.common.providers.DispatcherProvider
import com.watched.movie.R
import com.watched.movie.domain.GetPopularMoviesUseCase
import com.watched.movie.domain.GetTopXPopularMoviesUseCase
import com.watched.movie.mapper.MovieItemDomainUiMapper
import com.watched.movie.mapper.TopXMovieDomainUiMapper
import com.watched.movie.model.MovieSectionItemUiModel
import com.watched.movie.model.TopXMovieUiModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MovieMainViewModel @Inject constructor(
    private val topXPopularMoviesUseCase: GetTopXPopularMoviesUseCase,
    private val topXPopularMovieMapper: TopXMovieDomainUiMapper,
    private val popularMoviesUseCase: GetPopularMoviesUseCase,
    private val movieItemMapper: MovieItemDomainUiMapper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _topXMovie = MutableLiveData<TopXMovieUiModel>()
    val topXMovie: LiveData<TopXMovieUiModel> = _topXMovie

    private val _popularMovies = MutableLiveData<MovieSectionItemUiModel>()
    val popularMovies: LiveData<MovieSectionItemUiModel> = _popularMovies

    private val topXPopularExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("GetTopXPopularMoviesUseCase throws $exception.")
    }

    private val popularMoviesExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("GetPopularMoviesUseCase throws $exception.")
    }

    init {
        getTopXPopularMovies()
        getPopularMovies()
    }

    private fun getPopularMovies(forceRefresh: Boolean = false) {
        viewModelScope.launch(
            dispatcherProvider.io() + popularMoviesExceptionHandler
        ) {
            val popularMovies =
                popularMoviesUseCase.execute(GetPopularMoviesUseCase.Args(forceRefresh))
            val popularUiModelList = popularMovies.movies.map(movieItemMapper::mapToUiModel)
            _popularMovies.postValue(
                MovieSectionItemUiModel(
                    title = R.string.popular_movies,
                    items = popularUiModelList
                )
            )
        }
    }

    private fun getTopXPopularMovies() {
        viewModelScope.launch(
            dispatcherProvider.io() + topXPopularExceptionHandler
        ) {
            val topXMovie = topXPopularMoviesUseCase.execute()
            val uiModel = topXPopularMovieMapper.mapToUiModel(topXMovie)
            _topXMovie.postValue(uiModel)
        }
    }
}
