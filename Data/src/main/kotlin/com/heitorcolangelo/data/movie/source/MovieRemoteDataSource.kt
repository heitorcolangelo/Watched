package com.heitorcolangelo.data.movie.source

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.common.source.RemoteDataSource
import com.heitorcolangelo.data.movie.model.MovieDataModel

interface MovieRemoteDataSource : RemoteDataSource {
    suspend fun getMovies(page: Int): PageDataModel<MovieDataModel>
    suspend fun getLatestMovie(): MovieDataModel?
    suspend fun getMovie(movieId: String): MovieDataModel
}
