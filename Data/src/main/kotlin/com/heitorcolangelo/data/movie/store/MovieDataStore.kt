package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.common.store.DataStore
import com.heitorcolangelo.data.movie.model.MovieDataModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface MovieDataStore : DataStore {
    fun getMovies(page: Int): Observable<PageDataModel<MovieDataModel>>
    fun saveMovies(movies: List<MovieDataModel>): Completable
    fun getMovie(movieId: String): Observable<MovieDataModel>

    companion object {
        internal const val PAGE_SIZE = 20
    }
}
