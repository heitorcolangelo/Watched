package com.heitorcolangelo.movie.domain

import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import com.heitorcolangelo.test.common.presentation.TestExecutionThreadProvider
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class GetMovieUseCaseTest {
    private val repository: MovieRepository = mockk(relaxed = true)
    private val threadProvider: ExecutionThreadProvider = TestExecutionThreadProvider()
    private val useCase = GetMovieUseCase(repository, threadProvider)

    @Test
    fun `WHEN build THEN get movie from repository`() {
        val movieId = "movieId"
        val arg = GetMovieUseCase.Arg(movieId)

        useCase.build(arg).test()

        verify { repository.getMovie(movieId) }
    }
}
