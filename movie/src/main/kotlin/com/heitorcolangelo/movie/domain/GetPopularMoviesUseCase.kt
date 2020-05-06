package com.heitorcolangelo.movie.domain

import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import com.heitorcolangelo.domain.common.usecase.NoArgsObservableUseCase
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    threadProvider: ExecutionThreadProvider
) : NoArgsObservableUseCase<PageDomainModel<MovieDomainModel>>(threadProvider) {
    override fun build(): Observable<PageDomainModel<MovieDomainModel>> {
        return repository.getMovies(MoviesSortOption.Popularity)
    }
}
