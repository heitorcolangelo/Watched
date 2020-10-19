package com.heitorcolangelo.movie.domain

import com.heitorcolangelo.domain.common.usecase.UseCase
import com.heitorcolangelo.domain.common.usecase.UseCaseArgs
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<MovieDomainModel, GetMovieUseCase.Arg>() {
    override suspend fun invoke(args: Arg): MovieDomainModel {
        return repository.getMovie(args.movieId)
    }

    data class Arg(val movieId: String) : UseCaseArgs
}
