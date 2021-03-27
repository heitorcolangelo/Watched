package com.watched.movie.data.source

import com.watched.data.common.model.PageDataModel
import com.watched.data.common.model.SortOptionsDataModel
import com.watched.data.common.source.PagedDataSource
import com.watched.data.common.source.RemoteDataSource
import com.watched.movie.data.model.MovieDataModel

interface MovieRemoteDataSource : RemoteDataSource, PagedDataSource {
    suspend fun getMovies(
        page: Int,
        sortOption: SortOptionsDataModel
    ): PageDataModel<MovieDataModel>

    suspend fun getMovie(movieId: String): MovieDataModel
}
