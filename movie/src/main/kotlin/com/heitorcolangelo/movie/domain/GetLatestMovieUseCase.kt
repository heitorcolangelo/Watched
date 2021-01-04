package com.heitorcolangelo.movie.domain

import com.heitorcolangelo.domain.common.usecase.UseCase
import com.heitorcolangelo.domain.common.usecase.UseCaseArgs
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import javax.inject.Inject

class GetLatestMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<MovieDomainModel, GetLatestMovieUseCase.Args>() {
    override suspend fun invoke(args: Args): MovieDomainModel {
        try {
            return repository.getLatestMovie(args.forceRefresh)
        } catch (e: Exception) {
            e.printStackTrace()
            throw NoLatestMovieException()
        }
    }

    class Args(val forceRefresh: Boolean = false) : UseCaseArgs

    class NoLatestMovieException : Throwable()
}