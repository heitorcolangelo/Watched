package com.heitorcolangelo.movie.domain

import com.heitorcolangelo.domain.common.scheduler.ExecutionThreadProvider
import com.heitorcolangelo.domain.common.usecase.ObservableUseCase
import com.heitorcolangelo.domain.common.usecase.UseCaseArgs
import com.heitorcolangelo.domain.movie.model.MovieDomainModel
import com.heitorcolangelo.domain.movie.repository.MovieRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
    threadProvider: ExecutionThreadProvider
) : ObservableUseCase<MovieDomainModel, GetMovieUseCase.Arg>(threadProvider) {
    override fun build(args: Arg): Observable<MovieDomainModel> {
        return repository.getMovie(args.movieId)
    }

    data class Arg(val movieId: String) : UseCaseArgs
}
