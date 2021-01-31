package com.watched.data.local.movie

import com.watched.data.local.common.LocalDataSourceImpl
import com.watched.data.local.config.dao.ConfigDao
import com.watched.data.local.movie.dao.MovieDao
import com.watched.data.local.movie.entity.MovieEntity
import com.watched.data.local.movie.mapper.MovieEntityDataMapper
import com.watched.data.movie.model.MovieDataModel
import com.watched.data.movie.model.SortOptionsDataModel
import com.watched.data.movie.source.MovieLocalDataSource
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val mapper: MovieEntityDataMapper,
    configDao: ConfigDao
) : LocalDataSourceImpl(configDao), MovieLocalDataSource {

    override val firstPage: Int = FIRST_PAGE

    override suspend fun isDataCached(): Boolean {
        return movieDao.getMovies().isNotEmpty()
    }

    override suspend fun saveMovies(movies: List<MovieDataModel>) {
        val movieEntities = movies.map(mapper::mapToEntity)
        movieDao.saveMovies(movieEntities)
    }

    override suspend fun getMovies(
        page: Int,
        pageSize: Int,
        sortOption: SortOptionsDataModel
    ): List<MovieDataModel> {
        val pageToRequest = pageToRequest(page)
        val offset = getOffset(pageToRequest, pageSize)
        val movies = movieDao.getPagedMovies(pageSize, offset).sort(sortOption)
        return movies.map(mapper::mapToDataModel)
    }

    override suspend fun getMovie(movieId: String): MovieDataModel {
        val movie = movieDao.getMovie(movieId)
        return mapper.mapToDataModel(movie)
    }

    override suspend fun clear() {
        super.clear()
        movieDao.clearMovies()
    }

    private fun List<MovieEntity>.sort(sortOption: SortOptionsDataModel): List<MovieEntity> {
        return sortedBy {
            when (sortOption) {
                SortOptionsDataModel.Popularity -> it.popularity
                SortOptionsDataModel.TopRated -> it.voteAverage
            }
        }
    }

    companion object {
        const val FIRST_PAGE = 0
    }
}
