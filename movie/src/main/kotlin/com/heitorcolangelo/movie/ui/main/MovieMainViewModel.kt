package com.heitorcolangelo.movie.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heitorcolangelo.domain.common.providers.DispatcherProvider
import com.heitorcolangelo.movie.domain.GetLatestMovieUseCase
import com.heitorcolangelo.movie.mapper.LatestMovieDomainUiMapper
import com.heitorcolangelo.movie.model.LatestMovieUiModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MovieMainViewModel @Inject constructor(
    private val latestMovieUseCase: GetLatestMovieUseCase,
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
            val latestMovie = latestMovieUseCase.execute(GetLatestMovieUseCase.Args())
            val uiModel = latestMovieMapper.mapToUiModel(latestMovie)
            _latestMovie.postValue(uiModel)
        }
    }
}