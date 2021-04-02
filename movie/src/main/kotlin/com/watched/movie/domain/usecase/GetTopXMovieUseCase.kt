package com.watched.movie.domain.usecase

import com.watched.domain.common.model.SortOptionsDomainModel
import com.watched.domain.common.usecase.NoArgUseCase
import com.watched.movie.domain.MovieRepository
import com.watched.movie.domain.model.MovieTopXDomainModel
import javax.inject.Inject
import kotlin.random.Random

class GetTopXMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) : NoArgUseCase<MovieTopXDomainModel>() {
    override suspend fun invoke(): MovieTopXDomainModel {
        val nextMovieIndex = Random.nextInt(RANGE_START, RANGE_END)
        val movies = repository.getMovies(sortOption = SortOptionsDomainModel.Popularity)

        return MovieTopXDomainModel(nextMovieIndex, movies[nextMovieIndex])
    }

    companion object {
        const val RANGE_START = 0
        const val RANGE_END = 9
    }
}
