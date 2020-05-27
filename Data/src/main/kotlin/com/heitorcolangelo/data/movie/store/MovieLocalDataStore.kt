package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.common.store.LocalDataStore
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieLocalData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MovieLocalDataStore @Inject constructor(
    private val localData: MovieLocalData
) : MovieDataStore, LocalDataStore {

    override fun getMovies(page: Int): Observable<PageDataModel<MovieDataModel>> {
        return localData.getMovies(page, MovieDataStore.PAGE_SIZE).map {
            PageDataModel(page = page, pageSize = MovieDataStore.PAGE_SIZE, items = it)
        }
    }

    override fun saveMovies(movies: List<MovieDataModel>): Completable {
        return localData.saveMovies(movies)
            .andThen(localData.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun isDataValid(): Observable<Boolean> {
        return localData.isCacheValid(System.currentTimeMillis())
    }

    override fun getMovie(movieId: String): Observable<MovieDataModel> {
        return localData.getMovie(movieId)
    }

    override fun clear(): Completable {
        return localData.clear()
    }
}
