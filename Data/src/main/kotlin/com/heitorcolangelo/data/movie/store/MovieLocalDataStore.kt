package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.common.store.LocalDataStore
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieLocalDataSource
import javax.inject.Inject

class MovieLocalDataStore @Inject constructor(
    private val dataSource: MovieLocalDataSource
) : MovieDataStore, LocalDataStore {

    override suspend fun getMovies(
        page: Int,
        forceRefresh: Boolean
    ): PageDataModel<MovieDataModel> {
        val movies = dataSource.getMovies(page, MovieDataStore.PAGE_SIZE)
        return PageDataModel(page = page, pageSize = MovieDataStore.PAGE_SIZE, items = movies)
    }

    override suspend fun getLatestMovie(forceRefresh: Boolean): MovieDataModel? {
        return dataSource.getLatestMovie()
    }

    override suspend fun saveMovies(movies: List<MovieDataModel>) {
        dataSource.saveMovies(movies)
        dataSource.setLastCacheTime(System.currentTimeMillis())
    }

    override suspend fun isDataValid(): Boolean {
        return dataSource.isCacheValid(System.currentTimeMillis())
    }

    override suspend fun getMovie(movieId: String): MovieDataModel {
        return dataSource.getMovie(movieId)
    }

    override suspend fun clear() {
        dataSource.clear()
    }
}
