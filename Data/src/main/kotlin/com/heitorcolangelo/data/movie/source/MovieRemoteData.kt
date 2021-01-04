package com.heitorcolangelo.data.movie.source

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.common.source.RemoteData
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import io.reactivex.rxjava3.core.Observable

interface MovieRemoteData : RemoteData {
    suspend fun getMovies(page: Int): PageDataModel<MovieDataModel>
    suspend fun getLatestMovie(): MovieDataModel
    fun getMovie(movieId: String): Observable<MovieDataModel>
}
