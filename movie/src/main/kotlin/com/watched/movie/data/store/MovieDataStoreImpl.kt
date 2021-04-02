package com.watched.movie.data.store

import com.watched.data.common.model.PageDataModel
import com.watched.data.common.model.SortOptionsDataModel
import com.watched.movie.data.model.MovieDataModel
import javax.inject.Inject

class MovieDataStoreImpl @Inject constructor(
    private val localDataStore: MovieLocalDataStore,
    private val remoteDataStore: MovieRemoteDataStore,
) : MovieDataStore {
    override suspend fun getMoviePage(
        page: Int,
        sortOption: SortOptionsDataModel,
        forceRefresh: Boolean
    ): PageDataModel<MovieDataModel> {
        if (forceRefresh || !localDataStore.isDataValid()) {
            saveMovies(remoteDataStore.getMoviePage(page, sortOption).items)
        }

        return localDataStore.getMoviePage(page, sortOption)
    }

    override suspend fun saveMovies(movies: List<MovieDataModel>) {
        return localDataStore.saveMovies(movies)
    }

    override suspend fun getMovie(movieId: String): MovieDataModel {
        if (!localDataStore.isDataValid()) {
            saveMovies(
                listOf(remoteDataStore.getMovie(movieId))
            )
        }
        return localDataStore.getMovie(movieId)
    }
}
