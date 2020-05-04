package com.heitorcolangelo.data.movie

import com.heitorcolangelo.data.movie.mapper.PagedMovieDataDomainMapper
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.store.MovieDataStore
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class MovieRepositoryImplTest {
    private val mapper: PagedMovieDataDomainMapper = mockk(relaxed = true)
    private val dataStore: MovieDataStore = mockk(relaxed = true)

    private val repo = MovieRepositoryImpl(mapper, dataStore)

    @Test
    fun `WHEN get movies THEN get movies from data store`() {
        repo.getMovies(MoviesSortOption.MostRecent).test()

        verify { dataStore.getMovies() }
    }

    @Test
    fun `WHEN data store returns THEN map to domain model`() {
        val dataMovies = listOf<MovieDataModel>()
        every { dataStore.getMovies() } returns Observable.just(dataMovies)

        repo.getMovies(MoviesSortOption.MostRecent).test()

        verify { mapper.mapToDomainModel(dataMovies) }
    }
}