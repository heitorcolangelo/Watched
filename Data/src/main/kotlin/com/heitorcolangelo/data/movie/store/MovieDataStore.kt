package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.common.store.DataStore
import com.heitorcolangelo.data.movie.model.MovieDataModel
import io.reactivex.rxjava3.core.Completable

interface MovieDataStore : DataStore {
    fun getMovies(page: Int, forceRefresh: Boolean = false): PageDataModel<MovieDataModel>
    fun saveMovies(movies: List<MovieDataModel>): Completable
    fun getMovie(movieId: String): MovieDataModel

    companion object {
        internal const val PAGE_SIZE = 20
    }
}
