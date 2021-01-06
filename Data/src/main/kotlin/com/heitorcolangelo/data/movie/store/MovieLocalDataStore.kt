package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.common.store.LocalDataStore
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieLocalData
import javax.inject.Inject

class MovieLocalDataStore @Inject constructor(
    private val localData: MovieLocalData
) : MovieDataStore, LocalDataStore {

    override suspend fun getMovies(
        page: Int,
        forceRefresh: Boolean
    ): PageDataModel<MovieDataModel> {
        val movies = localData.getMovies(page, MovieDataStore.PAGE_SIZE)
        return PageDataModel(page = page, pageSize = MovieDataStore.PAGE_SIZE, items = movies)
    }

    override suspend fun getLatestMovie(forceRefresh: Boolean): MovieDataModel {
        TODO("Not yet implemented")
    }

    override suspend fun saveMovies(movies: List<MovieDataModel>) {
        localData.saveMovies(movies)
        localData.setLastCacheTime(System.currentTimeMillis())
    }

    override suspend fun isDataValid(): Boolean {
        return localData.isCacheValid(System.currentTimeMillis())
    }

    override suspend fun getMovie(movieId: String): MovieDataModel {
        return localData.getMovie(movieId)
    }

    override suspend fun clear() {
        localData.clear()
    }
}
