package com.watched.movie.domain

import com.watched.domain.common.usecase.UseCase
import com.watched.domain.common.usecase.UseCaseArgs
import com.watched.domain.movie.model.MovieDomainModel
import com.watched.domain.movie.model.MovieListDomainModel
import com.watched.domain.movie.model.SortOptionsDomainModel
import com.watched.domain.movie.repository.MovieRepository
import javax.inject.Inject

class GetSortedMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<MovieListDomainModel, GetSortedMoviesUseCase.Args>() {

    override suspend fun invoke(args: Args): MovieListDomainModel {
        val sortedMovies: List<MovieDomainModel> = repository.getMovies(
            sortOption = args.sortedOptions,
            forceRefresh = args.forceRefresh
        )

        return MovieListDomainModel(
            id = sortedMovies.hashCode().toString(),
            items = sortedMovies,
            sortOptions = args.sortedOptions
        )
    }

    class Args(
        val forceRefresh: Boolean,
        val sortedOptions: SortOptionsDomainModel
    ) : UseCaseArgs
}
