package com.watched.movie.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.watched.domain.common.providers.DispatcherProvider
import com.watched.movie.domain.GetLatestMovieUseCase
import com.watched.movie.domain.GetTopXPopularMoviesUseCase
import com.watched.movie.mapper.LatestMovieDomainUiMapper
import com.watched.movie.model.LatestMovieUiModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MovieMainViewModel @Inject constructor(
    private val topXPopularMoviesUseCase: GetTopXPopularMoviesUseCase,
    private val latestMovieMapper: LatestMovieDomainUiMapper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {
    private val _latestMovie = MutableLiveData<LatestMovieUiModel>()
    val latestMovie: LiveData<LatestMovieUiModel> = _latestMovie

    private val latestMovieExceptionHandler = CoroutineExceptionHandler { _, exception ->
        if (exception is GetLatestMovieUseCase.NoLatestMovieException) {
            // Handle exception
            println("NoLatestMovieException was thrown.")
        }
    }

    init {
        getLatestMovie()
    }

    private fun getLatestMovie() {
        viewModelScope.launch(
            dispatcherProvider.io() + latestMovieExceptionHandler
        ) {
            val topXMovie = topXPopularMoviesUseCase.execute()
            val uiModel = latestMovieMapper.mapToUiModel(topXMovie.movie)
            _latestMovie.postValue(uiModel)
        }
    }
}
