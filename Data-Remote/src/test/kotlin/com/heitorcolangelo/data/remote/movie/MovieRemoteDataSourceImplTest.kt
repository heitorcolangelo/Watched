package com.heitorcolangelo.data.remote.movie

import com.heitorcolangelo.data.remote.common.model.PageResponseModel
import com.heitorcolangelo.data.remote.factory.MovieResponseModelFactory
import com.heitorcolangelo.data.remote.movie.api.MovieApiService
import com.heitorcolangelo.data.remote.movie.mapper.MoviePageResponseDataMapper
import com.heitorcolangelo.data.remote.movie.mapper.MovieResponseDataMapper
import com.heitorcolangelo.data.remote.movie.model.MovieResponseModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MovieRemoteDataSourceImplTest {
    private val pageMapper: MoviePageResponseDataMapper = mockk(relaxed = true)
    private val movieMapper: MovieResponseDataMapper = mockk(relaxed = true)
    private val api: MovieApiService = mockk(relaxed = true)
    private val remoteData = MovieRemoteDataSourceImpl(pageMapper, movieMapper, api)

    @Test
    fun `WHEN get movies THEN get Popular movies`() {
        runBlocking { remoteData.getMovies(1) }

        coVerify { api.getPopular() }
    }

    @Test
    fun `WHEN get movies page THEN get Popular movies page`() {
        val page = 2

        runBlocking { remoteData.getMovies(page) }

        coVerify { api.getPopular(any()) }
    }

    @Test
    fun `WHEN api return movies THEN map to data model`() {
        val moviePageResponse = mockk<PageResponseModel<MovieResponseModel>>(relaxed = true)
        coEvery { api.getPopular() } returns moviePageResponse

        runBlocking { remoteData.getMovies(1) }

        coVerify { pageMapper.mapToPageDataModel(moviePageResponse) }
    }

    @Test
    fun `WHEN get movie THEN get movie from api`() {
        val movieId = "movieId"

        runBlocking { remoteData.getMovie(movieId) }

        coVerify { api.getMovie(movieId) }
    }

    @Test
    fun `WHEN get movie returns THEN map to data model`() {
        val response = MovieResponseModelFactory.make()
        val movieId = response.id.toString()
        coEvery { api.getMovie(movieId) } returns response

        runBlocking { remoteData.getMovie(movieId) }

        coVerify { movieMapper.mapToDataModel(response) }
    }
}
