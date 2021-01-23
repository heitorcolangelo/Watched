package com.watched.data.movie.store

import com.watched.data.common.model.PageDataModel
import com.watched.data.common.store.DataStore
import com.watched.data.movie.model.MovieDataModel
import com.watched.data.movie.model.SortOptionsDataModel

interface MovieDataStore : DataStore {
    suspend fun getMoviePage(
        page: Int,
        sortOption: SortOptionsDataModel,
        forceRefresh: Boolean = false
    ): PageDataModel<MovieDataModel>

    suspend fun getLatestMovie(forceRefresh: Boolean = false): MovieDataModel?
    suspend fun saveMovies(movies: List<MovieDataModel>)
    suspend fun getMovie(movieId: String): MovieDataModel

    companion object {
        internal const val PAGE_SIZE = 50
    }
}
