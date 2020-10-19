package com.heitorcolangelo.domain.movie.repository

import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.model.MoviesSortOption

interface MovieRepository {
    suspend fun getMovies(page: Int, sortOption: MoviesSortOption, forceRefresh: Boolean = false): PageDomainModel<MovieDomainModel>
    suspend fun getMovie(movieId: String): MovieDomainModel
}
