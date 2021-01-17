package com.watched.data.movie.source

import com.watched.data.common.model.PageDataModel
import com.watched.data.common.source.RemoteDataSource
import com.watched.data.movie.model.MovieDataModel

interface MovieRemoteDataSource : RemoteDataSource {
    suspend fun getMovies(page: Int): PageDataModel<MovieDataModel>
    suspend fun getLatestMovie(): MovieDataModel?
    suspend fun getMovie(movieId: String): MovieDataModel
}
