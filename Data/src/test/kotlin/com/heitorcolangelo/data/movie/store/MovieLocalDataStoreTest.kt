package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieLocalData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class MovieLocalDataStoreTest {
    private val localData: MovieLocalData = mockk(relaxed = true)
    private val dataStore = MovieLocalDataStore(localData)

    @Test
    fun `WHEN get movies THEN get local data movies`() {
        dataStore.getMovies().test()

        verify { localData.getMovies() }
    }

    @Test
    fun `WHEN save movies THEN save movies to local data`() {
        val movies = listOf<MovieDataModel>()
        dataStore.saveMovies(movies).test()

        verify { localData.saveMovies(movies) }
    }

    @Test
    fun `WHEN isDataValid is called THEN local data isCacheValid is called`() {
        every { localData.isCacheValid(any()) } returns Observable.just(true)

        val testObserver = dataStore.isDataValid().test()
        testObserver.assertValue(true)

        verify { localData.isCacheValid(any()) }
    }
}