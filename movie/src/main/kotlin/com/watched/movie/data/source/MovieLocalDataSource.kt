package com.watched.movie.data.source

import com.watched.data.common.model.SortOptionsDataModel
import com.watched.data.common.source.LocalDataSource
import com.watched.data.common.source.PagedDataSource
import com.watched.movie.data.model.MovieDataModel

interface MovieLocalDataSource : LocalDataSource, PagedDataSource {
    override val dataConfigId: String
        get() = MovieLocalDataSource::class.java.name

    suspend fun saveMovies(movies: List<MovieDataModel>)

    suspend fun getMovies(page: Int, pageSize: Int, sortOption: SortOptionsDataModel): List<MovieDataModel>

    suspend fun getMovie(movieId: String): MovieDataModel
}
