package com.heitorcolangelo.data.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.heitorcolangelo.data.common.mapper.PageDataDomainMapper
import com.heitorcolangelo.data.movie.mapper.MovieDataDomainMapper
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.source.MoviePagingSource
import com.heitorcolangelo.data.movie.store.MovieDataStore
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepositoryImpl @Inject constructor(
    private val pageMapper: PageDataDomainMapper<MovieDataModel, MovieDomainModel>,
    private val movieMapper: MovieDataDomainMapper,
    private val dataStore: MovieDataStore,
    private val moviePagingSource: MoviePagingSource
) : MovieRepository {
    override suspend fun getMovies(
        sortOption: MoviesSortOption,
        forceRefresh: Boolean
    ): Flow<PagingData<MovieDomainModel>> {
        return Pager(
            config = PagingConfig(pageSize = MovieDataStore.PAGE_SIZE),
            pagingSourceFactory = { moviePagingSource }
        ).flow.map(pageMapper::mapToPageDomainModel)
    }

    override suspend fun getMovie(movieId: String): MovieDomainModel {
        val movie = dataStore.getMovie(movieId)
        return movieMapper.mapToDomainModel(movie)
    }
}
