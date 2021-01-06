package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.movie.source.MovieRemoteData
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MovieRemoteDataStoreTest {
    private val remoteData: MovieRemoteData = mockk(relaxed = true)
    private val dataStore = MovieRemoteDataStore(remoteData)

    @Test
    fun `WHEN get movies THEN get movies from remote data`() {
        runBlocking { dataStore.getMovies(0) }

        coVerify {
            remoteData.getMovies(1)
        }
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `WHEN save movies THEN throw exception`() {
        runBlocking { dataStore.saveMovies(listOf()) }
    }
}
