package com.heitorcolangelo.movie.domain

import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import com.heitorcolangelo.domain.common.usecase.PagedUseCase
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    threadProvider: ExecutionThreadProvider
) : PagedUseCase<MovieDomainModel>(threadProvider) {
    override fun build(args: Args): Observable<PageDomainModel<MovieDomainModel>> {
        val nextPage = nextPage(args)
        return repository.getMovies(nextPage, MoviesSortOption.Popularity, args.forceRefresh)
    }
}
