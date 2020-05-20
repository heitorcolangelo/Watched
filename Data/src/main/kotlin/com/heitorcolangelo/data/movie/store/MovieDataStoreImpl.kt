package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.movie.model.MovieDataModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MovieDataStoreImpl @Inject constructor(
    private val localDataStore: MovieLocalDataStore,
    private val remoteDataStore: MovieRemoteDataStore
) : MovieDataStore {
    override fun getMovies(page: Int): Observable<PageDataModel<MovieDataModel>> {
        return localDataStore.isDataValid().flatMap { isDataValid ->
            if (isDataValid) {
                localDataStore.getMovies(page)
            } else {
                remoteDataStore.getMovies(page).flatMap {
                    saveMovies(it.items).andThen(Observable.just(it))
                }
            }
        }
    }

    override fun saveMovies(movies: List<MovieDataModel>): Completable {
        return localDataStore.saveMovies(movies)
    }

    override fun getMovie(movieId: String): Observable<MovieDataModel> {
        return localDataStore.isDataValid().flatMap { isDataValid ->
            if (isDataValid) {
                localDataStore.getMovie(movieId)
            } else {
                remoteDataStore.getMovie(movieId).flatMap {
                    saveMovies(listOf(it)).andThen(Observable.just(it))
                }
            }
        }
    }
}
