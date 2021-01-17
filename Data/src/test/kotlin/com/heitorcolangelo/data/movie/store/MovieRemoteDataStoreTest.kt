package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.movie.source.MovieRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MovieRemoteDataStoreTest {
    private val dataSource: MovieRemoteDataSource = mockk(relaxed = true)
    private val dataStore = MovieRemoteDataStore(dataSource)
    private val movieId = "movieId"

    @Test
    fun `WHEN get movie THEN get movies from remote data`() {
        coEvery { dataSource.getMovie(movieId) } returns mockk(relaxed = true)

        runBlocking { dataStore.getMovie(movieId) }

        coVerify { dataSource.getMovie(movieId) }
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `WHEN save movies THEN throw exception`() {
        runBlocking { dataStore.saveMovies(listOf()) }
    }
}
