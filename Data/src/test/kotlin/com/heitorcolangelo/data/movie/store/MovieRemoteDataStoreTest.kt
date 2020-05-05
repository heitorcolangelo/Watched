package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.movie.source.MovieRemoteData
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class MovieRemoteDataStoreTest {
    private val remoteData: MovieRemoteData = mockk(relaxed = true)
    private val dataStore = MovieRemoteDataStore(remoteData)

    @Test
    fun `WHEN get movies THEN get movies from remote data`() {
        dataStore.getMovies().test()

        verify {
            remoteData.getMovies()
        }
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `WHEN save movies THEN throw exception`() {
        dataStore.saveMovies(listOf()).test()
    }
}
