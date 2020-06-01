package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.movie.model.MovieDataModel
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import javax.inject.Inject

class MovieDataStoreImpl @Inject constructor(
    private val localDataStore: MovieLocalDataStore,
    private val remoteDataStore: MovieRemoteDataStore
) : MovieDataStore {
    override fun getMovies(
        page: Int,
        forceRefresh: Boolean
    ): Observable<PageDataModel<MovieDataModel>> {
        if (forceRefresh) {
            return localDataStore.clear().andThen(getMoviesRemote(page))
        }

        return Observable.zip(
            localDataStore.isDataValid(),
            localDataStore.getMovies(page),
            BiFunction { isDataValid: Boolean, localPage: PageDataModel<MovieDataModel> ->
                val isPageLocallyAvailable = localPage.items.isNotEmpty()
                if (isPageLocallyAvailable) {
                    if (isDataValid) {
                        Observable.just(localPage)
                    } else {
                        localDataStore.clear().andThen(getMoviesRemote(page))
                    }
                } else {
                    getMoviesRemote(page)
                }
            }
        ).flatMap { it }
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

    private fun getMoviesRemote(page: Int): Observable<PageDataModel<MovieDataModel>> {
        return remoteDataStore.getMovies(page).flatMap {
            saveMovies(it.items).andThen(Observable.just(it))
        }
    }
}
