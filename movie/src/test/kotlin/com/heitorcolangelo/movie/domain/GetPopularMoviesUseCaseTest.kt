package com.heitorcolangelo.movie.domain

import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import com.heitorcolangelo.test.common.presentation.TestExecutionThreadProvider
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetPopularMoviesUseCaseTest {
    private val repository: MovieRepository = mockk(relaxed = true)
    private val threadProvider: ExecutionThreadProvider = TestExecutionThreadProvider()
    private val useCase = GetPopularMoviesUseCase(repository, threadProvider)

    @Test
    fun `WHEN build THEN get popular movies from repo`() {
        val slot = slot<MoviesSortOption>()

        useCase.build()

        verify { repository.getMovies(capture(slot)) }
        assertTrue(slot.isCaptured)
        assertEquals(MoviesSortOption.Popularity, slot.captured)
    }
}