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
    ): PageDataModel<MovieDataModel> {
        if (forceRefresh) {
            return localDataStore.clear().andThen(getMoviesRemote(page)).blockingFirst()
        }

        return Observable.zip(
            localDataStore.isDataValid(),
            Observable.just(localDataStore.getMovies(page)),
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
        ).flatMap { it }.blockingFirst()
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

    private fun getMoviesRemote(page: Int): Observable<PageDataModel<MovieDataModel>> {
        val movies = remoteDataStore.getMovies(page)
        saveMovies(movies.items)
        return Observable.just(movies)
    }
}
