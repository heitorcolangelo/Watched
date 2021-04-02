package com.watched.movie.data.source.remote

import com.watched.data.common.model.SortOptionsDataModel
import com.watched.data.remote.common.model.PageResponseModel
import com.watched.data.remote.movie.api.MovieApiService
import com.watched.data.remote.movie.model.MovieResponseModel
import com.watched.movie.data.factory.MovieResponseModelFactory
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
    fun `WHEN sortOptions is Popularity THEN call api getPopular`() {
        val page = 2
        coEvery { api.getPopular(any()) } returns mockk(relaxed = true)

        runBlocking { remoteData.getMovies(page, SortOptionsDataModel.Popularity) }

        coVerify { api.getPopular(any()) }
    }

    @Test(expected = UnsupportedOperationException::class)
    fun `WHEN sortOptions is TopRated THEN throw exception`() {
        val page = 2
        coEvery { api.getPopular(any()) } returns mockk(relaxed = true)

        runBlocking { remoteData.getMovies(page, SortOptionsDataModel.TopRated) }

        coVerify { api.getPopular(any()) }
    }

    @Test
    fun `WHEN api return movies THEN map to data model`() {
        val response = PageResponseModel<MovieResponseModel>(emptyList())
        coEvery { api.getPopular(any()) } returns response

        runBlocking { remoteData.getMovies(1, SortOptionsDataModel.Popularity) }

        coVerify { pageMapper.mapToPageDataModel(response) }
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
