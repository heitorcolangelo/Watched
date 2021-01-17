package com.heitorcolangelo.data.local.movie

import com.heitorcolangelo.data.local.common.LocalDataSourceImpl
import com.heitorcolangelo.data.local.config.dao.ConfigDao
import com.heitorcolangelo.data.local.movie.dao.MovieDao
import com.heitorcolangelo.data.local.movie.mapper.MovieEntityDataMapper
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MovieLocalDataSource
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val mapper: MovieEntityDataMapper,
    configDao: ConfigDao
) : LocalDataSourceImpl(configDao), MovieLocalDataSource {
    override suspend fun isDataCached(): Boolean {
        return movieDao.getMovies().isNotEmpty()
    }

    override suspend fun saveMovies(movies: List<MovieDataModel>) {
        val movieEntities = movies.map(mapper::mapToEntity)
        movieDao.saveMovies(movieEntities)
    }

    override suspend fun getMovies(page: Int, pageSize: Int): List<MovieDataModel> {
        val offset = getOffset(page, pageSize)
        val movies = movieDao.getPagedMovies(pageSize, offset)
        return movies.map(mapper::mapToDataModel)
    }

    override suspend fun getMovie(movieId: String): MovieDataModel {
        val movie = movieDao.getMovie(movieId)
        return mapper.mapToDataModel(movie)
    }

    override suspend fun getLatestMovie(): MovieDataModel? {
        return movieDao.getMovies().minByOrNull { it.releaseDate }?.let {
            mapper.mapToDataModel(it)
        }
    }

    override suspend fun clear() {
        super.clear()
        movieDao.clearMovies()
    }
}
