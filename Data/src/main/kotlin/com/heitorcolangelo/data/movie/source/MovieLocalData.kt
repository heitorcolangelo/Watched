package com.heitorcolangelo.data.movie.source

import com.heitorcolangelo.data.common.source.LocalData
import com.heitorcolangelo.data.movie.model.MovieDataModel

interface MovieLocalData : LocalData {
    override val dataConfigId: String
        get() = MovieLocalData::class.java.name

    suspend fun saveMovies(movies: List<MovieDataModel>)

    suspend fun getMovies(page: Int, pageSize: Int): List<MovieDataModel>

    suspend fun getMovie(movieId: String): MovieDataModel
}
