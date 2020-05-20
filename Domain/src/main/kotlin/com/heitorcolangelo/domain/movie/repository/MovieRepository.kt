package com.heitorcolangelo.domain.movie.repository

import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import io.reactivex.rxjava3.core.Observable

interface MovieRepository {
    fun getMovies(page: Int, sortOption: MoviesSortOption): Observable<PageDomainModel<MovieDomainModel>>
    fun getMovie(movieId: String): Observable<MovieDomainModel>
}
