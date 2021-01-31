package com.watched.data.movie.store

import com.watched.data.common.model.PageDataModel
import com.watched.data.common.store.LocalDataStore
import com.watched.data.movie.model.MovieDataModel
import com.watched.data.movie.model.SortOptionsDataModel
import com.watched.data.movie.source.MovieLocalDataSource
import javax.inject.Inject

class MovieLocalDataStore @Inject constructor(
    private val dataSource: MovieLocalDataSource
) : MovieDataStore, LocalDataStore {

    override suspend fun getMoviePage(
        page: Int,
        sortOption: SortOptionsDataModel,
        forceRefresh: Boolean
    ): PageDataModel<MovieDataModel> {
        val movies = dataSource.getMovies(page, MovieDataStore.PAGE_SIZE, sortOption)
        return PageDataModel(page = page, pageSize = MovieDataStore.PAGE_SIZE, items = movies)
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
