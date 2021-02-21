package com.watched.movie.domain

import com.watched.common.test.relaxedMockk
import com.watched.domain.movie.model.SortOptionsDomainModel
import com.watched.domain.movie.repository.MovieRepository
import com.watched.movie.factory.MovieDomainModelFactory
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetSortedMoviesUseCaseTest {
    private val repo: MovieRepository = mockk()
    private val useCase = GetSortedMoviesUseCase(repo)

    @Test
    fun `WHEN execute THEN call repo getMovies`() {
        coEvery { repo.getMovies(any(), any(), any()) } returns relaxedMockk()
        val args = GetSortedMoviesUseCase.Args(false, SortOptionsDomainModel.Popularity)

        runBlocking { useCase.execute(args) }

        coVerify {
            repo.getMovies(
                sortOption = args.sortedOptions,
                forceRefresh = args.forceRefresh
            )
        }
    }

    @Test
    fun `WHEN repo returns THEN return MovieListDomainModel`() {
        val movies = MovieDomainModelFactory.makeList(3)
        coEvery { repo.getMovies(any(), any(), any()) } returns movies
        val args = GetSortedMoviesUseCase.Args(false, SortOptionsDomainModel.Popularity)

        val movieListDomainModel = runBlocking { useCase.execute(args) }

        assertEquals(args.sortedOptions, movieListDomainModel.sortOptions)
        assertEquals(movies, movieListDomainModel.items)
    }
}
