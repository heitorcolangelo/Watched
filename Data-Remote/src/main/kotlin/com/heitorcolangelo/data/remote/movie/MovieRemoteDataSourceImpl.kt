package com.heitorcolangelo.data.remote.movie

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieRemoteDataSource
import com.heitorcolangelo.data.remote.common.model.PageResponseModel
import com.heitorcolangelo.data.remote.movie.api.MovieApiService
import com.heitorcolangelo.data.remote.movie.mapper.MoviePageResponseDataMapper
import com.heitorcolangelo.data.remote.movie.mapper.MovieResponseDataMapper
import com.heitorcolangelo.data.remote.movie.model.MovieResponseModel
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val pageMapper: MoviePageResponseDataMapper,
    private val movieMapper: MovieResponseDataMapper,
    private val api: MovieApiService
) : MovieRemoteDataSource {
    override suspend fun getMovies(page: Int): PageDataModel<MovieDataModel> {
        val popular: PageResponseModel<MovieResponseModel> = api.getPopular(page)
        return pageMapper.mapToPageDataModel(popular)
    }

    override suspend fun getMovie(movieId: String): MovieDataModel {
        return movieMapper.mapToDataModel(api.getMovie(movieId))
    }

    override suspend fun getLatestMovie(): MovieDataModel {
        return movieMapper.mapToDataModel(api.getLatestMovie())
    }
}
