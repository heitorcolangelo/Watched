package com.heitorcolangelo.data.movie

import com.heitorcolangelo.data.common.mapper.PageDataDomainMapper
import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.store.MovieDataStore
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class MovieRepositoryImplTest {
    private val mapper: PageDataDomainMapper<MovieDataModel, MovieDomainModel> =
        mockk(relaxed = true)
    private val dataStore: MovieDataStore = mockk(relaxed = true)

    private val repo = MovieRepositoryImpl(mapper, dataStore)

    @Test
    fun `WHEN get movies THEN get movies from data store`() {
        repo.getMovies(MoviesSortOption.MostRecent).test()

        verify { dataStore.getMovies() }
    }

    @Test
    fun `WHEN data store returns THEN map to domain model`() {
        val movies = listOf<MovieDataModel>()
        val moviePage = PageDataModel(movies)
        every { dataStore.getMovies() } returns Observable.just(moviePage)

        repo.getMovies(MoviesSortOption.MostRecent).test()

        verify { mapper.mapToPageDomainModel(moviePage) }
    }
}