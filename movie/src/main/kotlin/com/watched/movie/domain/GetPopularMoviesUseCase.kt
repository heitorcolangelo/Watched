package com.watched.movie.domain

import com.watched.domain.common.usecase.UseCase
import com.watched.domain.common.usecase.UseCaseArgs
import com.watched.domain.movie.model.PopularMoviesDomainModel
import com.watched.domain.movie.model.SortOptionsDomainModel
import com.watched.domain.movie.repository.MovieRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<PopularMoviesDomainModel, GetPopularMoviesUseCase.Args>() {

    override suspend fun invoke(args: Args): PopularMoviesDomainModel {
        return PopularMoviesDomainModel(
            repository.getMovies(
                sortOption = SortOptionsDomainModel.Popularity,
                forceRefresh = args.forceRefresh
            )
        )
    }

    class Args(val forceRefresh: Boolean) : UseCaseArgs
}
