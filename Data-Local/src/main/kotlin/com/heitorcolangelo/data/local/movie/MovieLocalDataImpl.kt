package com.heitorcolangelo.data.local.movie

import com.heitorcolangelo.data.local.common.LocalDataImpl
import com.heitorcolangelo.data.local.config.dao.ConfigDao
import com.heitorcolangelo.data.local.movie.dao.MovieDao
import com.heitorcolangelo.data.local.movie.mapper.MovieEntityDataMapper
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieLocalData
import hu.akarnokd.rxjava3.bridge.RxJavaBridge
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class MovieLocalDataImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val mapper: MovieEntityDataMapper,
    configDao: ConfigDao
) : LocalDataImpl(configDao), MovieLocalData {
    override fun isDataCached(): Single<Boolean> {
        return RxJavaBridge.toV3Flowable(movieDao.getMovies())
            .map { it.isNotEmpty() }.firstOrError()
    }

    override fun saveMovies(movies: List<MovieDataModel>): Completable {
        val movieEntities = movies.map(mapper::mapToEntity)
        return RxJavaBridge.toV3Completable(movieDao.saveMovies(movieEntities))
    }

    override fun getMovies(): Observable<List<MovieDataModel>> {
        val movies = RxJavaBridge.toV3Flowable(movieDao.getMovies())
        return movies.toObservable().map { it.map(mapper::mapToDataModel) }
    }

    override fun getMovie(movieId: String): Observable<MovieDataModel> {
        val movies = RxJavaBridge.toV3Flowable(movieDao.getMovie(movieId))
        return movies.toObservable().map(mapper::mapToDataModel)
    }

    override fun clear(): Completable {
        return super.clear().andThen(
            RxJavaBridge.toV3Completable(movieDao.clearMovies())
        )
    }
}
