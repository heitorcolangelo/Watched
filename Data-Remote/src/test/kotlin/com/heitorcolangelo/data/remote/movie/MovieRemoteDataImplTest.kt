package com.heitorcolangelo.data.remote.movie

import com.heitorcolangelo.data.remote.common.model.PageResponseModel
import com.heitorcolangelo.data.remote.movie.api.MovieApiService
import com.heitorcolangelo.data.remote.movie.mapper.MoviePageResponseDataMapper
import com.heitorcolangelo.data.remote.movie.model.MovieResponseModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Observable
import org.junit.Test

class MovieRemoteDataImplTest {
    private val mapper: MoviePageResponseDataMapper = mockk(relaxed = true)
    private val api: MovieApiService = mockk(relaxed = true)
    private val remoteData = MovieRemoteDataImpl(mapper, api)

    @Test
    fun `WHEN get movies THEN get Popular movies`() {
        remoteData.getMovies().test()

        verify { api.getPopular() }
    }

    @Test
    fun `WHEN api return movies THEN map to data model`() {
        val moviePageResponse = mockk<PageResponseModel<MovieResponseModel>>(relaxed = true)
        every { api.getPopular() } returns Observable.just(moviePageResponse)

        remoteData.getMovies().test()

        verify { mapper.mapToPageDataModel(moviePageResponse) }
    }
}
