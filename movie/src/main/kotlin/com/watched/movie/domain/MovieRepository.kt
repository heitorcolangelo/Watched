package com.watched.movie.domain

import com.watched.domain.common.model.PageDomainModel
import com.watched.domain.common.model.SortOptionsDomainModel
import com.watched.movie.domain.model.MovieDomainModel

interface MovieRepository {
    suspend fun getMovies(
        page: Int = PageDomainModel.FIRST_PAGE,
        sortOption: SortOptionsDomainModel,
        forceRefresh: Boolean = false
    ): List<MovieDomainModel>

    suspend fun getMovie(movieId: String): MovieDomainModel
}
