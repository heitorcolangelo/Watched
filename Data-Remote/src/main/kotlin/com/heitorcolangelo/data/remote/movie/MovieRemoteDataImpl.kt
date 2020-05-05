package com.heitorcolangelo.data.remote.movie

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieRemoteData
import com.heitorcolangelo.data.remote.movie.api.MovieApiService
import com.heitorcolangelo.data.remote.movie.mapper.MoviePageResponseDataMapper
import io.reactivex.rxjava3.core.Observable

class MovieRemoteDataImpl(
    private val mapper: MoviePageResponseDataMapper,
    private val api: MovieApiService
) : MovieRemoteData {
    override fun getMovies(): Observable<PageDataModel<MovieDataModel>> {
        return api.getPopular().map(mapper::mapToPageDataModel)
    }
}
