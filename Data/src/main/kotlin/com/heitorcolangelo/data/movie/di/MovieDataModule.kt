package com.heitorcolangelo.data.movie.di

import com.heitorcolangelo.data.common.mapper.DataDomainMapper
import com.heitorcolangelo.data.common.mapper.PageDataDomainMapper
import com.heitorcolangelo.data.common.mapper.PageDataDomainMapperImpl
import com.heitorcolangelo.data.movie.MovieRepositoryImpl
import com.heitorcolangelo.data.movie.mapper.MovieDataDomainMapper
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieLocalData
import com.heitorcolangelo.data.movie.store.MovieDataStore
import com.heitorcolangelo.data.movie.store.MovieDataStoreImpl
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Module
abstract class MovieDataModule {

    @Binds
    abstract fun bindMovieDataStore(impl: MovieDataStoreImpl): MovieDataStore

    @Binds
    abstract fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    abstract fun bindMovieDataDomainMapper(impl: MovieDataDomainMapper): DataDomainMapper<MovieDataModel, MovieDomainModel>

    @Module
    companion object {
        @Provides
        fun providePagedMovieDataDomainMapper(itemMapper: MovieDataDomainMapper): PageDataDomainMapper<MovieDataModel, MovieDomainModel> {
            return PageDataDomainMapperImpl(itemMapper)
        }

        @Provides
        fun provideMovieLocalData(): MovieLocalData {
            return object : MovieLocalData {
                override fun saveMovies(movies: List<MovieDataModel>): Completable {
                    return Completable.complete()
                }

                override fun getMovies(): Observable<List<MovieDataModel>> {
                    return Observable.just(listOf())
                }

                override fun clear(): Completable {
                    return Completable.complete()
                }

                override fun setLastCacheTime(lastCacheTime: Long): Completable {
                    return Completable.complete()
                }

                override fun isCacheExpired(currentTime: Long): Observable<Boolean> {
                    return Observable.just(true)
                }

                override fun isDataCached(): Single<Boolean> {
                    return Single.just(false)
                }
            }
        }
    }
}
