package com.watched.data.remote.movie

import com.watched.data.remote.common.model.PageResponseModel
import com.watched.data.remote.factory.MovieResponseModelFactory
import com.watched.data.remote.movie.api.MovieApiService
import com.watched.data.remote.movie.mapper.MoviePageResponseDataMapper
import com.watched.data.remote.movie.mapper.MovieResponseDataMapper
import com.watched.data.remote.movie.model.MovieResponseModel
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
        coEvery { api.getPopular() } returns mockk(relaxed = true)

        runBlocking { remoteData.getMovies(1) }

        coVerify { api.getPopular() }
    }

    @Test
    fun `WHEN get movies page THEN get Popular movies page`() {
        val page = 2
        coEvery { api.getPopular(any()) } returns mockk(relaxed = true)

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
        coEvery { api.getMovie(any()) } returns mockk(relaxed = true)

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
