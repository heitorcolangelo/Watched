package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.movie.model.MovieDataModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MovieDataStoreImpl @Inject constructor(
    private val localDataStore: MovieLocalDataStore,
    private val remoteDataStore: MovieRemoteDataStore
) : MovieDataStore {
    override fun getMovies(): Observable<List<MovieDataModel>> {
        return localDataStore.isDataValid().flatMap { isDataValid ->
            if (isDataValid) {
                localDataStore.getMovies()
            } else {
                remoteDataStore.getMovies().flatMap {
                    saveMovies(it).andThen(Observable.just(it))
                }
            }
        }
    }

    override fun saveMovies(movies: List<MovieDataModel>): Completable {
        return localDataStore.saveMovies(movies)
    }
}
