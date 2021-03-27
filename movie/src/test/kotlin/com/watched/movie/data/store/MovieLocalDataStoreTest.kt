package com.watched.movie.data.store

import com.watched.movie.data.model.MovieDataModel
import com.watched.movie.data.source.MovieLocalDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MovieLocalDataStoreTest {
    private val dataSource: MovieLocalDataSource = mockk(relaxed = true)
    private val dataStore = MovieLocalDataStore(dataSource)
    private val movieId = "movieId"

    @Test
    fun `WHEN get movie THEN get from data source`() {
        runBlocking { dataStore.getMovie(movieId) }

        coVerify { dataSource.getMovie(movieId) }
    }

    @Test
    fun `WHEN save movies THEN save movies to data source`() {
        val movies = listOf<MovieDataModel>()
        runBlocking { dataStore.saveMovies(movies) }

        coVerify { dataSource.saveMovies(movies) }
    }

    @Test
    fun `WHEN isDataValid is called THEN local data isCacheValid is called`() {
        coEvery { dataSource.isCacheValid(any()) } returns true

        runBlocking { dataStore.isDataValid() }

        coVerify { dataSource.isCacheValid(any()) }
    }

    @Test
    fun `WHEN getMovie THEN get from data source`() {
        runBlocking { dataStore.getMovie(movieId) }

        coVerify { dataSource.getMovie(movieId) }
    }
}
