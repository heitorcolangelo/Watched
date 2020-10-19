package com.heitorcolangelo.data.movie

import com.heitorcolangelo.data.common.dispatcher.DispatcherProvider
import com.heitorcolangelo.data.common.mapper.PageDataDomainMapper
import com.heitorcolangelo.data.movie.mapper.MovieDataDomainMapper
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.store.MovieDataStore
import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val pageMapper: PageDataDomainMapper<MovieDataModel, MovieDomainModel>,
    private val movieMapper: MovieDataDomainMapper,
    private val dataStore: MovieDataStore,
    private val dispatcherProvider: DispatcherProvider
) : MovieRepository {
    override suspend fun getMovies(page: Int, sortOption: MoviesSortOption, forceRefresh: Boolean): PageDomainModel<MovieDomainModel> {
        return withContext(dispatcherProvider.io()) {
            val movies = dataStore.getMovies(page, forceRefresh)
            pageMapper.mapToPageDomainModel(movies)
        }
    }

    override suspend fun getMovie(movieId: String): MovieDomainModel {
        return withContext(dispatcherProvider.io()) {
            val movie = dataStore.getMovie(movieId)
            movieMapper.mapToDomainModel(movie)
        }
    }
}
