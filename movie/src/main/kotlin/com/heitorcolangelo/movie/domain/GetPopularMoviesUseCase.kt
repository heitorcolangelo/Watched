package com.heitorcolangelo.movie.domain

import com.heitorcolangelo.domain.common.model.PageDomainModel
import com.heitorcolangelo.domain.common.usecase.PagedUseCase
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.model.MoviesSortOption
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : PagedUseCase<MovieDomainModel>() {
    override suspend fun invoke(args: Args): PageDomainModel<MovieDomainModel> {
        val nextPage = nextPage(args)
        return repository.getMovies(nextPage, MoviesSortOption.Popularity, args.forceRefresh)
    }
}
