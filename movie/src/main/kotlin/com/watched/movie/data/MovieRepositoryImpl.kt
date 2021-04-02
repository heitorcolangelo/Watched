package com.watched.movie.data

import com.watched.data.common.mapper.SortOptionsDataDomainMapper
import com.watched.domain.common.model.SortOptionsDomainModel
import com.watched.movie.data.store.MovieDataStore
import com.watched.movie.domain.MovieRepository
import com.watched.movie.domain.mapper.MovieDataDomainMapper
import com.watched.movie.domain.model.MovieDomainModel
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieMapper: MovieDataDomainMapper,
    private val sortOptionsMapper: SortOptionsDataDomainMapper,
    private val dataStore: MovieDataStore,
) : MovieRepository {
    override suspend fun getMovies(
        page: Int,
        sortOption: SortOptionsDomainModel,
        forceRefresh: Boolean
    ): List<MovieDomainModel> {
        return dataStore.getMoviePage(
            page = page,
            sortOption = sortOptionsMapper.mapToDataModel(sortOption),
            forceRefresh = forceRefresh
        ).items.map(movieMapper::mapToDomainModel)
    }

    override suspend fun getMovie(movieId: String): MovieDomainModel {
        val movie = dataStore.getMovie(movieId)
        return movieMapper.mapToDomainModel(movie)
    }
}
