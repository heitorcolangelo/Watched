package com.heitorcolangelo.data.movie.source

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.common.source.RemoteData
import com.heitorcolangelo.data.movie.model.MovieDataModel

interface MovieRemoteData : RemoteData {
    suspend fun getMovies(page: Int): PageDataModel<MovieDataModel>
    suspend fun getLatestMovie(): MovieDataModel
    suspend fun getMovie(movieId: String): MovieDataModel
}
