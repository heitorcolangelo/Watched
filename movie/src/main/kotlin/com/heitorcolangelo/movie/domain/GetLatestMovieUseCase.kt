package com.heitorcolangelo.movie.domain

import com.heitorcolangelo.domain.common.usecase.UseCase
import com.heitorcolangelo.domain.common.usecase.UseCaseArgs
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import javax.inject.Inject

class GetLatestMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : UseCase<MovieDomainModel, GetLatestMovieUseCase.Args>() {

    @Throws(NoLatestMovieException::class)
    override suspend fun invoke(args: Args): MovieDomainModel {
        val latestMovie = repository.getLatestMovie(args.forceRefresh)
        return latestMovie ?: throw NoLatestMovieException()
    }

    class Args(val forceRefresh: Boolean = false) : UseCaseArgs

    class NoLatestMovieException : Throwable()
}