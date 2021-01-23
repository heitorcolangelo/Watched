package com.watched.data.movie.store

import com.watched.data.common.model.PageDataModel
import com.watched.data.movie.model.MovieDataModel
import com.watched.data.movie.model.SortOptionsDataModel
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
            remoteDataStore.getMoviePage(getRemoteDataPage(page), sortOption).let {
                saveMovies(it.items)
            }
        }

        return localDataStore.getMoviePage(getLocalDataPage(page), sortOption)
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

    private fun getRemoteDataPage(domainPage: Int): Int {
        return domainPage + MovieRemoteDataStore.FIRST_PAGE_REMOTE
    }

    private fun getLocalDataPage(domainPage: Int): Int {
        return domainPage
    }

}
