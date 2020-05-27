package com.heitorcolangelo.data.remote.movie

import com.heitorcolangelo.data.remote.common.model.PageResponseModel
import com.heitorcolangelo.data.remote.factory.MovieResponseModelFactory
import com.heitorcolangelo.data.remote.movie.api.MovieApiService
import com.heitorcolangelo.data.remote.movie.mapper.MoviePageResponseDataMapper
import com.heitorcolangelo.data.remote.movie.mapper.MovieResponseDataMapper
import com.heitorcolangelo.data.remote.movie.model.MovieResponseModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class MovieRemoteDataImplTest {
    private val pageMapper: MoviePageResponseDataMapper = mockk(relaxed = true)
    private val movieMapper: MovieResponseDataMapper = mockk(relaxed = true)
    private val api: MovieApiService = mockk(relaxed = true)
    private val remoteData = MovieRemoteDataImpl(pageMapper, movieMapper, api)

    @Test
    fun `WHEN get movies THEN get Popular movies`() {
        remoteData.getMovies(1).test()

        verify { api.getPopular() }
    }

    @Test
    fun `WHEN get movies page THEN get Popular movies page`() {
        val page = 2

        remoteData.getMovies(page).test()

        val requestedPage = remoteData.getNextPage(page)
        verify { api.getPopular(requestedPage) }
    }

    @Test
    fun `WHEN api return movies THEN map to data model`() {
        val moviePageResponse = mockk<PageResponseModel<MovieResponseModel>>(relaxed = true)
        every { api.getPopular() } returns Observable.just(moviePageResponse)

        remoteData.getMovies(1).test()

        verify { pageMapper.mapToPageDataModel(moviePageResponse) }
    }

    @Test
    fun `WHEN get movie THEN get movie from api`() {
        val movieId = "movieId"

        remoteData.getMovie(movieId).test()

        verify { api.getMovie(movieId) }
    }

    @Test
    fun `WHEN get movie returns THEN map to data model`() {
        val response = MovieResponseModelFactory.make()
        val movieId = response.id.toString()
        every { api.getMovie(movieId) } returns Observable.just(response)

        remoteData.getMovie(movieId).test()

        verify { movieMapper.mapToDataModel(response) }
    }
}
