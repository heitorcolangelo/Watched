package com.heitorcolangelo.data.movie.source

import com.heitorcolangelo.data.common.source.LocalDataSource
import com.heitorcolangelo.data.movie.model.MovieDataModel

interface MovieLocalDataSource : LocalDataSource {
    override val dataConfigId: String
        get() = MovieLocalDataSource::class.java.name

    suspend fun saveMovies(movies: List<MovieDataModel>)

    suspend fun getMovies(page: Int, pageSize: Int): List<MovieDataModel>

    suspend fun getMovie(movieId: String): MovieDataModel

    suspend fun getLatestMovie(): MovieDataModel?
}
