package com.heitorcolangelo.movie.domain

import com.heitorcolangelo.domain.movie.repository.MovieRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetMovieUseCaseTest {
    private val repository: MovieRepository = mockk(relaxed = true)
    private val useCase = GetMovieUseCase(repository)

    @Test
    fun `WHEN build THEN get movie from repository`() {
        val movieId = "movieId"
        val arg = GetMovieUseCase.Arg(movieId)

        runBlocking {
            useCase.execute(arg)
        }

        coVerify { repository.getMovie(movieId) }
    }
}
