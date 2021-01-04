package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MovieDataStoreImpl @Inject constructor(
    private val localDataStore: MovieLocalDataStore,
    private val remoteDataStore: MovieRemoteDataStore
) : MovieDataStore {
    override suspend fun getMovies(
        page: Int,
        forceRefresh: Boolean
    ): PageDataModel<MovieDataModel> {
        return remoteDataStore.getMovies(page)
    }

    override suspend fun getLatestMovie(forceRefresh: Boolean): MovieDataModel {
        return remoteDataStore.getLatestMovie(forceRefresh)
    }

    override fun saveMovies(movies: List<MovieDataModel>): Completable {
        return localDataStore.saveMovies(movies)
    }

    override fun getMovie(movieId: String): MovieDataModel {
        val isDataValid = localDataStore.isDataValid().blockingFirst()
        return if (isDataValid) {
            localDataStore.getMovie(movieId)
        } else {
            remoteDataStore.getMovie(movieId).also {
                saveMovies(listOf(it)).andThen(Observable.just(it))
            }
        }
    }
}
