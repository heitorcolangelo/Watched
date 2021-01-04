package com.heitorcolangelo.data.remote.movie

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieRemoteData
import com.heitorcolangelo.data.remote.common.model.PageResponseModel
import com.heitorcolangelo.data.remote.movie.api.MovieApiService
import com.heitorcolangelo.data.remote.movie.mapper.MoviePageResponseDataMapper
import com.heitorcolangelo.data.remote.movie.mapper.MovieResponseDataMapper
import com.heitorcolangelo.data.remote.movie.model.MovieResponseModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MovieRemoteDataImpl @Inject constructor(
    private val pageMapper: MoviePageResponseDataMapper,
    private val movieMapper: MovieResponseDataMapper,
    private val api: MovieApiService
) : MovieRemoteData {
    override suspend fun getMovies(page: Int): PageDataModel<MovieDataModel> {
        val popular: PageResponseModel<MovieResponseModel> = api.getPopular(page)
        return pageMapper.mapToPageDataModel(popular)
    }

    override fun getMovie(movieId: String): Observable<MovieDataModel> {
        return api.getMovie(movieId).map(movieMapper::mapToDataModel)
    }

    override suspend fun getLatestMovie(): MovieDataModel {
        return movieMapper.mapToDataModel(api.getLatestMovie())
    }
}
