package com.heitorcolangelo.data.movie

import com.heitorcolangelo.data.common.mapper.PageDataDomainMapper
import com.heitorcolangelo.data.movie.model.MovieDataModel
import com.heitorcolangelo.data.movie.store.MovieDataStore
import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val mapper: PageDataDomainMapper<MovieDataModel, MovieDomainModel>,
    private val dataStore: MovieDataStore
) : MovieRepository {
    override fun getMovies(sortOption: MoviesSortOption): Observable<PageDomainModel<MovieDomainModel>> {
        return dataStore.getMovies().map(mapper::mapToPageDomainModel)
    }
}
