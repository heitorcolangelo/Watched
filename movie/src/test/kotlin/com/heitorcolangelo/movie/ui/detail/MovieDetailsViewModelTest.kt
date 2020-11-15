package com.heitorcolangelo.movie.ui.detail

import com.example.test.android.viewmodel.ViewModelTest
import com.heitorcolangelo.domain.common.providers.DispatcherProvider
import com.heitorcolangelo.movie.domain.GetMovieUseCase
import com.heitorcolangelo.movie.factory.MovieDetailsUiModelFactory
import com.heitorcolangelo.movie.factory.MovieDomainModelFactory
import com.heitorcolangelo.movie.mapper.MovieDetailsDomainUiMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest : ViewModelTest() {

    private val mapper: MovieDetailsDomainUiMapper = mockk(relaxed = true)
    private val useCase: GetMovieUseCase = mockk(relaxed = true)
    private val dispatcherProvider: DispatcherProvider = mockk(relaxed = true)
    private val viewModel = MovieDetailsViewModel(mapper, useCase, dispatcherProvider)

    @Test
    fun `WHEN view is ready THEN get movie details`() = runBlockingTest {
        val movieId = "movieId"
        viewModel.setMovieId(movieId)
        val argSlot = slot<GetMovieUseCase.Arg>()
        coEvery { useCase.get(any()) } returns mockk(relaxed = true)

        coVerify {
            useCase.get(capture(argSlot))
        }

        assertTrue(argSlot.isCaptured)
        assertEquals(movieId, argSlot.captured.movieId)
    }

    @Test
    fun `WHEN get movie returns THEN map to UiModel`() = runBlockingTest {
        val movieId = "movieId"
        viewModel.setMovieId(movieId)
        val domainModel = MovieDomainModelFactory.make()
        coEvery { useCase.get(any()) } returns domainModel

        verify { mapper.mapToUiModel(domainModel) }
    }

    @Test
    fun `WHEN get movie returns THEN post to LiveData`() {
        val movieId = "movieId"
        viewModel.setMovieId(movieId)
        coEvery { useCase.get(any()) } returns mockk(relaxed = true)
        val uiModel = MovieDetailsUiModelFactory.make()
        every { mapper.mapToUiModel(any()) } returns uiModel

        assertEquals(uiModel, viewModel.movie.value)
    }
}
