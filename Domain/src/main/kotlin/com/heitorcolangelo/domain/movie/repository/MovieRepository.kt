package com.heitorcolangelo.domain.movie.repository

import androidx.paging.PagingData
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies(
        sortOption: MoviesSortOption,
        forceRefresh: Boolean = false
    ): Flow<PagingData<MovieDomainModel>>

    suspend fun getLatestMovie(forceRefresh: Boolean = false): MovieDomainModel?

    suspend fun getMovie(movieId: String): MovieDomainModel
}
