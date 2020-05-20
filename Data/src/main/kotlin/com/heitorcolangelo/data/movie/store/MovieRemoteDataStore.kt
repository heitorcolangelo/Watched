package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.common.store.RemoteDataStore
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieRemoteData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MovieRemoteDataStore @Inject constructor(
    private val remoteData: MovieRemoteData
) : MovieDataStore, RemoteDataStore {
    override fun getMovies(page: Int): Observable<PageDataModel<MovieDataModel>> {
        return remoteData.getMovies(page)
    }

    override fun saveMovies(movies: List<MovieDataModel>): Completable {
        throw UnsupportedOperationException("Save is not supported by remote data source.")
    }

    override fun getMovie(movieId: String): Observable<MovieDataModel> {
        return remoteData.getMovie(movieId)
    }
}
