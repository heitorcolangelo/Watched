package com.watched.movie.ui.main

import com.watched.common.test.relaxedMockk
import com.watched.domain.movie.model.PopularMoviesDomainModel
import com.watched.domain.movie.model.TopXMovieDomainModel
import com.watched.movie.domain.GetPopularMoviesUseCase
import com.watched.movie.domain.GetTopXMovieUseCase
import com.watched.movie.mapper.MovieSectionDomainUiMapper
import com.watched.movie.mapper.TopXMovieDomainUiMapper
import com.watched.test.android.viewmodel.TestDispatcherProvider
import com.watched.test.android.viewmodel.ViewModelTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieMainViewModelTest : ViewModelTest() {

    private val topXMovieUseCase: GetTopXMovieUseCase = mockk()
    private val topXMovieMapper: TopXMovieDomainUiMapper = mockk()
    private val popularMoviesUseCase: GetPopularMoviesUseCase = mockk()
    private val movieSectionMapper: MovieSectionDomainUiMapper = mockk()
    private lateinit var viewModel: MovieMainViewModel

    @Before
    fun setUp() {
        coEvery { popularMoviesUseCase.execute(any()) } returns relaxedMockk()
        coEvery { topXMovieUseCase.execute() } returns relaxedMockk()
        every { topXMovieMapper.mapToUiModel(any()) } returns relaxedMockk()
        every { movieSectionMapper.mapToUiModel(any()) } returns relaxedMockk()
    }

    @Test
    fun `WHEN init THEN get popular movies`() {
        initViewModel()

        coVerify { popularMoviesUseCase.execute(any()) }
    }

    @Test
    fun `WHEN init THEN get top x movie`() {
        initViewModel()

        coVerify { topXMovieUseCase.execute() }
    }

    @Test
    fun `WHEN GetTopMovieXUseCase returns THEN map to ui model`() {
        val topXMovie = relaxedMockk<TopXMovieDomainModel>()
        coEvery { topXMovieUseCase.execute() } returns topXMovie

        initViewModel()

        coVerify { topXMovieMapper.mapToUiModel(topXMovie) }
    }

    @Test
    fun `WHEN GetPopularMoviesUseCase returns THEN map to ui model`() {
        val popularMovies = relaxedMockk<PopularMoviesDomainModel>()
        coEvery { popularMoviesUseCase.execute(any()) } returns popularMovies

        initViewModel()

        coVerify { movieSectionMapper.mapToUiModel(any()) }
    }

    private fun initViewModel() {
        viewModel = MovieMainViewModel(
            topXMovieUseCase,
            topXMovieMapper,
            popularMoviesUseCase,
            movieSectionMapper,
            TestDispatcherProvider
        )
    }
}
