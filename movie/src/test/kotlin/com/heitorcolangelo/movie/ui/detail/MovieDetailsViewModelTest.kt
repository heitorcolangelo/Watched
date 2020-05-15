package com.heitorcolangelo.movie.ui.detail

import androidx.lifecycle.MutableLiveData
import com.heitorcolangelo.movie.domain.GetMovieUseCase
import com.heitorcolangelo.movie.factory.MovieDetailsUiModelFactory
import com.heitorcolangelo.movie.factory.MovieDomainModelFactory
import com.heitorcolangelo.movie.mapper.MovieDetailsDomainUiMapper
import com.heitorcolangelo.movie.model.MovieDetailsUiModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test

/**
 * This test runs on AS but not if you use command line, so it's ignored.
 * Related with:
 * - https://issuetracker.google.com/issues/139441237
 * - https://github.com/android/app-bundle-samples/issues/11
 */
@Ignore("Read explanation above")
class MovieDetailsViewModelTest {

    private val mapper: MovieDetailsDomainUiMapper = mockk(relaxed = true)
    private val useCase: GetMovieUseCase = mockk(relaxed = true)
    private val movieLiveData: MutableLiveData<MovieDetailsUiModel> = mockk(relaxed = true)
    private val viewModel = MovieDetailsViewModel(mapper, useCase)
    private val observer = MovieDetailsViewModel.GetMovieObserver(movieLiveData, mapper)

    @Test
    fun `WHEN view is ready THEN get movie details`() {
        val movieId = "movieId"
        viewModel.setMovieId(movieId)
        val argSlot = slot<GetMovieUseCase.Arg>()

        viewModel.onViewReady()

        verify { useCase.execute(capture(argSlot), any()) }
        assertTrue(argSlot.isCaptured)
        assertEquals(movieId, argSlot.captured.movieId)
    }

    @Test
    fun `WHEN get movie returns THEN map to UiModel`() {
        val domainModel = MovieDomainModelFactory.make()

        observer.onNext(domainModel)

        verify { mapper.mapToUiModel(domainModel) }
    }

    @Test
    fun `WHEN get movie returns THEN post to LiveData`() {
        val uiModel = MovieDetailsUiModelFactory.make()
        every { mapper.mapToUiModel(any()) } returns uiModel

        observer.onNext(MovieDomainModelFactory.make())

        verify { movieLiveData.postValue(uiModel) }
    }
}