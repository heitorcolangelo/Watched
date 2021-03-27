package com.watched.movie.ui.detail

import com.watched.domain.common.providers.DispatcherProvider
import com.watched.movie.domain.usecase.GetMovieUseCase
import com.watched.movie.data.factory.MovieDetailsUiModelFactory
import com.watched.movie.data.factory.MovieDomainModelFactory
import com.watched.movie.ui.mapper.MovieDetailsDomainUiMapper
import com.watched.test.android.viewmodel.TestDispatcherProvider
import com.watched.test.android.viewmodel.ViewModelTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieDetailsViewModelTest : ViewModelTest() {

    private val mapper: MovieDetailsDomainUiMapper = mockk(relaxed = true)
    private val useCase: GetMovieUseCase = mockk(relaxed = true)
    private val dispatcherProvider: DispatcherProvider = TestDispatcherProvider
    private val viewModel = MovieDetailsViewModel(mapper, useCase, dispatcherProvider)

    @Test
    fun `WHEN view is ready THEN get movie details`() {
        val movieId = "movieId"
        val argSlot = slot<GetMovieUseCase.Arg>()
        coEvery { useCase.execute(any()) } returns mockk(relaxed = true)

        viewModel.setMovieId(movieId)

        coVerify {
            useCase.execute(capture(argSlot))
        }

        assertTrue(argSlot.isCaptured)
        assertEquals(movieId, argSlot.captured.movieId)
    }

    @Test
    fun `WHEN get movie returns THEN map to UiModel`() {
        val movieId = "movieId"
        val domainModel = MovieDomainModelFactory.make()
        coEvery { useCase.execute(any()) } returns domainModel

        viewModel.setMovieId(movieId)

        verify { mapper.mapToUiModel(domainModel) }
    }

    @Test
    fun `WHEN get movie returns THEN post to LiveData`() {
        val movieId = "movieId"
        coEvery { useCase.execute(any()) } returns mockk(relaxed = true)
        val uiModel = MovieDetailsUiModelFactory.make()
        every { mapper.mapToUiModel(any()) } returns uiModel

        viewModel.setMovieId(movieId)

        assertEquals(uiModel, viewModel.movie.value)
    }
}
