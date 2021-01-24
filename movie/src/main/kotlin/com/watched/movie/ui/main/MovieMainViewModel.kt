package com.watched.movie.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watched.domain.common.providers.DispatcherProvider
import com.watched.movie.domain.GetTopXPopularMoviesUseCase
import com.watched.movie.mapper.TopXMovieDomainUiMapper
import com.watched.movie.model.TopXMovieUiModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MovieMainViewModel @Inject constructor(
    private val topXPopularMoviesUseCase: GetTopXPopularMoviesUseCase,
    private val topXPopularMovieMapper: TopXMovieDomainUiMapper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _topXMovie = MutableLiveData<TopXMovieUiModel>()
    val topXMovie: LiveData<TopXMovieUiModel> = _topXMovie

    private val topXPopularExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("GetTopXPopularMoviesUseCase throws $exception.")
    }

    init {
        getTopXPopularMovies()
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
