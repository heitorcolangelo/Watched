package com.heitorcolangelo.data.remote.movie

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieRemoteData
import com.heitorcolangelo.data.remote.movie.api.MovieApiService
import com.heitorcolangelo.data.remote.movie.mapper.MoviePageResponseDataMapper
import com.heitorcolangelo.data.remote.movie.mapper.MovieResponseDataMapper
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MovieRemoteDataImpl @Inject constructor(
    private val pageMapper: MoviePageResponseDataMapper,
    private val movieMapper: MovieResponseDataMapper,
    private val api: MovieApiService
) : MovieRemoteData {
    override fun getMovies(): Observable<PageDataModel<MovieDataModel>> {
        return api.getPopular().map(pageMapper::mapToPageDataModel)
    }

    override fun getMovie(movieId: String): Observable<MovieDataModel> {
        return api.getMovie(movieId).map(movieMapper::mapToDataModel)
    }
}
