package com.heitorcolangelo.data.movie.store

import com.heitorcolangelo.data.common.model.PageDataModel
import com.heitorcolangelo.data.common.store.RemoteDataStore
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieRemoteDataSource
import javax.inject.Inject

class MovieRemoteDataStore @Inject constructor(
    private val dataSource: MovieRemoteDataSource
) : MovieDataStore, RemoteDataStore {
    override suspend fun getMovies(
        page: Int,
        forceRefresh: Boolean
    ): PageDataModel<MovieDataModel> {
        return dataSource.getMovies(page)
    }

    override suspend fun getLatestMovie(forceRefresh: Boolean): MovieDataModel? {
        return dataSource.getLatestMovie()
    }

    override suspend fun saveMovies(movies: List<MovieDataModel>) {
        throw UnsupportedOperationException("Save is not supported by remote data source.")
    }

    override suspend fun getMovie(movieId: String): MovieDataModel {
        return dataSource.getMovie(movieId)
    }
}
