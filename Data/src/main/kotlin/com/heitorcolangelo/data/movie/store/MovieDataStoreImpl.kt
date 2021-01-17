package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.movie.model.MovieDataModel
import javax.inject.Inject

class MovieDataStoreImpl @Inject constructor(
    private val localDataStore: MovieLocalDataStore,
    private val remoteDataStore: MovieRemoteDataStore,
) : MovieDataStore {
    override suspend fun getMovies(
        page: Int,
        forceRefresh: Boolean
    ): PageDataModel<MovieDataModel> {
        return remoteDataStore.getMovies(page)
    }

    override suspend fun getLatestMovie(forceRefresh: Boolean): MovieDataModel? {
        if (forceRefresh || !localDataStore.isDataValid()) {
            remoteDataStore.getLatestMovie()?.let {
                saveMovies(listOf(it))
            }
        }
        return localDataStore.getLatestMovie()
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
