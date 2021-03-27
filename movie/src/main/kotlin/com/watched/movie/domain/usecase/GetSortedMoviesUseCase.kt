package com.watched.movie.domain.usecase

import com.watched.domain.common.model.SortOptionsDomainModel
import com.watched.domain.common.usecase.UseCase
import com.watched.domain.common.usecase.UseCaseArgs
import com.watched.movie.domain.MovieRepository
import com.watched.movie.domain.model.MovieDomainModel
import com.watched.domain.media.SortedMediaDomainModel
import javax.inject.Inject

class GetSortedMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<SortedMediaDomainModel, GetSortedMoviesUseCase.Args>() {

    override suspend fun invoke(args: Args): SortedMediaDomainModel {
        val sortedMovies: List<MovieDomainModel> = repository.getMovies(
            sortOption = args.sortedOptions,
            forceRefresh = args.forceRefresh
        )

        return SortedMediaDomainModel(
            items = sortedMovies,
            sortOptions = args.sortedOptions
        )
    }

    class Args(
        val forceRefresh: Boolean,
        val sortedOptions: SortOptionsDomainModel
    ) : UseCaseArgs
}
