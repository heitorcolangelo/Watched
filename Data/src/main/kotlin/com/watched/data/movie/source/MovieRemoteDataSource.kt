package com.watched.data.movie.source

import com.watched.data.common.model.PageDataModel
import com.watched.data.common.source.PagedDataSource
import com.watched.data.common.source.RemoteDataSource
import com.watched.data.movie.model.MovieDataModel
import com.watched.data.movie.model.SortOptionsDataModel

interface MovieRemoteDataSource : RemoteDataSource, PagedDataSource {
    suspend fun getMovies(
        page: Int,
        sortOption: SortOptionsDataModel
    ): PageDataModel<MovieDataModel>

    suspend fun getMovie(movieId: String): MovieDataModel
}
