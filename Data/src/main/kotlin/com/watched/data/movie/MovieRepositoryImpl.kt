package com.watched.data.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.watched.data.common.mapper.PageDataDomainMapper
import com.watched.data.movie.mapper.MovieDataDomainMapper
import com.watched.data.movie.mapper.SortOptionsDataDomainMapper
import com.watched.data.movie.model.MovieDataModel
import com.watched.data.movie.source.MoviePagingSource
import com.watched.data.movie.store.MovieDataStore
import com.watched.domain.movie.model.MovieDomainModel
import com.watched.domain.movie.model.SortOptionsDomainModel
import com.watched.domain.movie.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val pageMapper: PageDataDomainMapper<MovieDataModel, MovieDomainModel>,
    private val movieMapper: MovieDataDomainMapper,
    private val sortOptionsMapper: SortOptionsDataDomainMapper,
    private val dataStore: MovieDataStore,
    private val moviePagingSource: MoviePagingSource
) : MovieRepository {
    override suspend fun getPagedMovies(
        sortOption: SortOptionsDomainModel,
        forceRefresh: Boolean
    ): Flow<PagingData<MovieDomainModel>> {
        return Pager(
            config = PagingConfig(pageSize = MovieDataStore.PAGE_SIZE),
            pagingSourceFactory = { moviePagingSource }
        ).flow.map(pageMapper::mapToPageDomainModel)
    }

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

    override suspend fun getLatestMovie(forceRefresh: Boolean): MovieDomainModel? {
        val latestMovie = dataStore.getLatestMovie(forceRefresh)
        return latestMovie?.let {
            movieMapper.mapToDomainModel(it)
        }
    }

    override suspend fun getMovie(movieId: String): MovieDomainModel {
        val movie = dataStore.getMovie(movieId)
        return movieMapper.mapToDomainModel(movie)
    }
}
