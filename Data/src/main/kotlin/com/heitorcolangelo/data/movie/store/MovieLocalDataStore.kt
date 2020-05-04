package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.common.store.LocalDataStore
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieLocalData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MovieLocalDataStore @Inject constructor(
    private val localData: MovieLocalData
) : MovieDataStore, LocalDataStore {

    override fun getMovies(): Observable<List<MovieDataModel>> {
        return localData.getMovies()
    }

    override fun saveMovies(movies: List<MovieDataModel>): Completable {
        return localData.saveMovies(movies)
    }

    override fun isDataValid(): Observable<Boolean> {
        return localData.isCacheValid(System.currentTimeMillis())
    }
}
