package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieLocalData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MovieLocalDataStoreTest {
    private val localData: MovieLocalData = mockk(relaxed = true)
    private val dataStore = MovieLocalDataStore(localData)

    @Test
    fun `WHEN get movies THEN get local data movies`() {
        runBlocking { dataStore.getMovies(0) }

        coVerify { localData.getMovies(0, 10) }
    }

    @Test
    fun `WHEN save movies THEN save movies to local data`() {
        val movies = listOf<MovieDataModel>()
        runBlocking { dataStore.saveMovies(movies) }

        coVerify { localData.saveMovies(movies) }
    }

    @Test
    fun `WHEN isDataValid is called THEN local data isCacheValid is called`() {
        coEvery { localData.isCacheValid(any()) } returns true

        runBlocking { dataStore.isDataValid() }

        coVerify { localData.isCacheValid(any()) }
    }

    @Test
    fun `WHEN getMovie THEN get local movie`() {
        val movieId = "movieId"

        runBlocking { dataStore.getMovie(movieId) }

        coVerify { localData.getMovie(movieId) }
    }
}
