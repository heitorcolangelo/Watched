package com.heitorcolangelo.data.movie

import com.heitorcolangelo.data.common.mapper.PageDataDomainMapper
import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.factory.MovieDataModelFactory
import com.heitorcolangelo.data.movie.mapper.MovieDataDomainMapper
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
    private val pageMapper: PageDataDomainMapper<MovieDataModel, MovieDomainModel> =
        mockk(relaxed = true)
    private val movieMapper: MovieDataDomainMapper = mockk(relaxed = true)
    private val dataStore: MovieDataStore = mockk(relaxed = true)

    private val repo = MovieRepositoryImpl(pageMapper, movieMapper, dataStore)

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

        verify { pageMapper.mapToPageDomainModel(moviePage) }
    }

    @Test
    fun `WHEN get movie THEN get movie from data store`() {
        val movieId = "movieId"

        repo.getMovie(movieId).test()

        verify { dataStore.getMovie(movieId) }
    }

    @Test
    fun `WHEN get movie returns THEN map to domain model`() {
        val movie = MovieDataModelFactory.make()
        val movieId = movie.id
        every { dataStore.getMovie(movieId) } returns Observable.just(movie)

        repo.getMovie(movieId).test()

        verify { movieMapper.mapToDomainModel(movie) }
    }
}
