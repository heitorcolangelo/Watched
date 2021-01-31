package com.watched.domain.movie.repository

import androidx.paging.PagingData
import com.watched.domain.common.model.PageDomainModel
import com.watched.domain.movie.model.MovieDomainModel
import com.watched.domain.movie.model.SortOptionsDomainModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPagedMovies(
        sortOption: SortOptionsDomainModel,
        forceRefresh: Boolean = false
    ): Flow<PagingData<MovieDomainModel>>

    suspend fun getMovies(
        page: Int = PageDomainModel.FIRST_PAGE,
        sortOption: SortOptionsDomainModel,
        forceRefresh: Boolean = false
    ): List<MovieDomainModel>

    suspend fun getLatestMovie(forceRefresh: Boolean = false): MovieDomainModel?

    suspend fun getMovie(movieId: String): MovieDomainModel
}
