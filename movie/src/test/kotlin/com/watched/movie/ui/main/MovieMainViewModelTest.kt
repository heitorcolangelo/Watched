package com.watched.movie.ui.main

import com.watched.common.test.relaxedMockk
import com.watched.domain.common.model.SortOptionsDomainModel
import com.watched.domain.media.SortedMediaDomainModel
import com.watched.movie.domain.model.MovieTopXDomainModel
import com.watched.movie.domain.usecase.GetSortedMoviesUseCase
import com.watched.movie.domain.usecase.GetTopXMovieUseCase
import com.watched.movie.ui.mapper.MovieTopXDomainUiMapper
import com.watched.presentation.media.mapper.MediaSectionDomainUiMapper
import com.watched.test.android.viewmodel.TestDispatcherProvider
import com.watched.test.android.viewmodel.ViewModelTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieMainViewModelTest : ViewModelTest() {

    private val topXMovieUseCase: GetTopXMovieUseCase = mockk()
    private val topXMovieMapper: MovieTopXDomainUiMapper = mockk()
    private val sortedMoviesUseCase: GetSortedMoviesUseCase = mockk()
    private val movieSectionMapper: MediaSectionDomainUiMapper = mockk()
    private lateinit var viewModel: MovieMainViewModel

    @Before
    fun setUp() {
        coEvery { sortedMoviesUseCase.execute(any()) } returns relaxedMockk()
        coEvery { topXMovieUseCase.execute() } returns relaxedMockk()
        every { topXMovieMapper.mapToUiModel(any()) } returns relaxedMockk()
        every { movieSectionMapper.mapToUiModel(any()) } returns relaxedMockk()
    }

    @Test
    fun `WHEN init THEN get popular movies`() {
        initViewModel()

        coVerify { sortedMoviesUseCase.execute(any()) }
    }

    @Test
    fun `WHEN init THEN get top x movie`() {
        initViewModel()

        coVerify { topXMovieUseCase.execute() }
    }

    @Test
    fun `WHEN GetTopMovieXUseCase returns THEN map to UiModel`() {
        val topXMovie = relaxedMockk<MovieTopXDomainModel>()
        coEvery { topXMovieUseCase.execute() } returns topXMovie

        initViewModel()

        coVerify { topXMovieMapper.mapToUiModel(topXMovie) }
    }

    @Test
    fun `WHEN init viewModel THEN get movies by popularity`() {
        val args = GetSortedMoviesUseCase.Args(
            forceRefresh = false,
            sortedOptions = SortOptionsDomainModel.Popularity
        )
        coEvery { sortedMoviesUseCase.execute(any()) } returns relaxedMockk()

        initViewModel()

        val argsSlot = slot<GetSortedMoviesUseCase.Args>()
        coVerifySequence {
            sortedMoviesUseCase.execute(capture(argsSlot))
            sortedMoviesUseCase.execute(any())
        }
        assertEquals(args.sortedOptions, argsSlot.captured.sortedOptions)
    }

    @Test
    fun `WHEN init viewModel THEN get movies by top rated`() {
        val args = GetSortedMoviesUseCase.Args(
            forceRefresh = false,
            sortedOptions = SortOptionsDomainModel.TopRated
        )
        coEvery { sortedMoviesUseCase.execute(any()) } returns relaxedMockk()

        initViewModel()

        val slot = slot<GetSortedMoviesUseCase.Args>()
        coVerify { sortedMoviesUseCase.execute(capture(slot)) }
        assertEquals(SortOptionsDomainModel.TopRated, args.sortedOptions)
    }

    @Test
    fun `WHEN popular movies returns THEN map to UiModel`() {
        val popularMovies = relaxedMockk<SortedMediaDomainModel>()
        val args = GetSortedMoviesUseCase.Args(
            forceRefresh = false,
            sortedOptions = SortOptionsDomainModel.Popularity
        )
        conditionalReturn(args, popularMovies)

        initViewModel()

        coVerify { movieSectionMapper.mapToUiModel(popularMovies) }
    }

    @Test
    fun `WHEN top rated movies returns THEN map to UiModel`() {
        val topRatedMovies = relaxedMockk<SortedMediaDomainModel>()
        val args = GetSortedMoviesUseCase.Args(
            forceRefresh = false,
            sortedOptions = SortOptionsDomainModel.TopRated
        )
        conditionalReturn(args, topRatedMovies)

        initViewModel()

        coVerify { movieSectionMapper.mapToUiModel(topRatedMovies) }
    }

    private fun conditionalReturn(
        expectedArgs: GetSortedMoviesUseCase.Args,
        expectedReturn: SortedMediaDomainModel
    ) {
        val argSlot = slot<GetSortedMoviesUseCase.Args>()
        coEvery { sortedMoviesUseCase.execute(capture(argSlot)) } answers {
            if (argSlot.captured.sortedOptions == expectedArgs.sortedOptions) {
                expectedReturn
            } else {
                relaxedMockk()
            }
        }
    }

    private fun initViewModel() {
        viewModel = MovieMainViewModel(
            topXMovieUseCase,
            topXMovieMapper,
            sortedMoviesUseCase,
            movieSectionMapper,
            TestDispatcherProvider
        )
    }
}
