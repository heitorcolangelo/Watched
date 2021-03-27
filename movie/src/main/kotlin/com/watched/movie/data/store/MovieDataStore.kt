package com.watched.movie.data.store

import com.watched.data.common.model.PageDataModel
import com.watched.data.common.model.SortOptionsDataModel
import com.watched.data.common.store.DataStore
import com.watched.movie.data.model.MovieDataModel

interface MovieDataStore : DataStore {
    suspend fun getMoviePage(
        page: Int,
        sortOption: SortOptionsDataModel,
        forceRefresh: Boolean = false
    ): PageDataModel<MovieDataModel>

    suspend fun saveMovies(movies: List<MovieDataModel>)
    suspend fun getMovie(movieId: String): MovieDataModel

    companion object {
        internal const val PAGE_SIZE = 50
    }
}
